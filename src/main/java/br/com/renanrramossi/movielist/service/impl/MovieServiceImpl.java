package br.com.renanrramossi.movielist.service.impl;

import static br.com.renanrramossi.movielist.common.MovieConstants.MAX_RESPONSE_KEY;
import static br.com.renanrramossi.movielist.common.MovieConstants.MIN_RESPONSE_KEY;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renanrramossi.movielist.model.Movie;
import br.com.renanrramossi.movielist.model.ProducerStatisticDTO;
import br.com.renanrramossi.movielist.repository.MovieRepository;
import br.com.renanrramossi.movielist.service.MovieService;
import io.micrometer.core.instrument.util.StringUtils;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	private final Map<String, ProducerStatisticDTO> result = new HashMap<>();

	@Override
	public Map<String, ProducerStatisticDTO> findAll() {

		final List<Movie> movies = getListWithoutDuplicatedItems(movieRepository.findAll());

		if (movies.isEmpty()) {
			result.put(MIN_RESPONSE_KEY, new ProducerStatisticDTO());
			result.put(MAX_RESPONSE_KEY, new ProducerStatisticDTO());
			return new HashMap<>();
		}

		final List<Movie> auxiliaryList = new ArrayList<>();

		for (final Movie movie : movies) {
			auxiliaryList.addAll(
					movies.stream().filter(m -> hasSameProducer(movie, m) && auxiliaryList.indexOf(movie) == -1)
							.collect(Collectors.toList()));
		}

		return setResultValues(auxiliaryList, result);
	}

	private Map<String, ProducerStatisticDTO> setResultValues(final List<Movie> movies,
			final Map<String, ProducerStatisticDTO> result) {

		for (final Movie movie : movies) {

			final List<Movie> moviesFound = movies.stream()
					.filter(m -> movie.getProducers().contains(m.getProducers()))
					.collect(Collectors.toList());

			if (!moviesFound.isEmpty() && moviesFound.size() > 1) {

				final Optional<Movie> oldestMovieOptional = moviesFound.stream()
						.min(Comparator.comparingLong(Movie::getYear));
				final Optional<Movie> greaterInterval = moviesFound.stream()
						.max(Comparator.comparingLong(Movie::getYear));

				if (oldestMovieOptional.isPresent() && greaterInterval.isPresent()) {
					validateValues(oldestMovieOptional, greaterInterval);
				}
			}
		}

		return result;
	}

	private void validateValues(final Optional<Movie> oldestMovieOptional, final Optional<Movie> greaterInterval) {
		final Movie oldest = oldestMovieOptional.get();
		final Movie newest = greaterInterval.get();

		if (!oldest.getYear().equals(newest.getYear())) {
			final ProducerStatisticDTO producer = ProducerStatisticDTO.builder()
					.interval(newest.getYear() - oldest.getYear()).followingWin(newest.getYear())
					.previousWin(oldest.getYear()).producer(oldest.getProducers()).build();

			final ProducerStatisticDTO producerStatisticDTOMax = result.get(MAX_RESPONSE_KEY);
			final ProducerStatisticDTO producerStatisticDTOMin = result.get(MIN_RESPONSE_KEY);

			if (producerStatisticDTOMax != null) {
				if (producerStatisticDTOMax.getInterval() < producer.getInterval()) {
					result.put(MAX_RESPONSE_KEY, producer);
				} else {
					result.put(MAX_RESPONSE_KEY, producerStatisticDTOMax);
				}
			} else {
				result.put(MAX_RESPONSE_KEY, producer);
			}

			if (producerStatisticDTOMin != null) {
				if (producerStatisticDTOMin.getInterval() > producer.getInterval()) {
					result.put(MIN_RESPONSE_KEY, producer);
				} else {
					result.put(MIN_RESPONSE_KEY, producerStatisticDTOMin);
				}
			} else {
				result.put(MIN_RESPONSE_KEY, producer);
			}
		}
	}

	private List<Movie> getListWithoutDuplicatedItems(final List<Movie> movies) {
		return movies.stream()
				.filter(movie -> StringUtils.isNotBlank(movie.getWinner()))
				.collect(Collectors.toList());
	}

	private boolean hasSameProducer(final Movie movie, Movie m) {
		return movie.getProducers().contains(m.getProducers());
	}
}
