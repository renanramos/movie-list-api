package br.com.renanrrramossi.movielist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renanrrramossi.movielist.model.Movie;
import br.com.renanrrramossi.movielist.repository.MovieRepository;
import br.com.renanrrramossi.movielist.service.MovieService;
import reactor.core.publisher.Flux;

@Service
public class MovieServiceImpl implements MovieService<Movie> {

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public Flux<Movie> findAll() {
		return null;
	}

	@Override
	public void initializeDataBase(final Movie movie) {
		movieRepository.save(movie);
	}

}
