package br.com.renanrrramossi.movielist.infra;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import br.com.renanrrramossi.movielist.model.Movie;

@Component
public class MovieDataProcessor implements ItemProcessor<Movie, Movie> {

	@Override
	public Movie process(Movie item) throws Exception {
		return item;
	}
}
