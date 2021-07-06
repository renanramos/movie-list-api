package br.com.renanrramossi.movielist.infra;

import java.util.Optional;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import br.com.renanrramossi.movielist.model.Movie;

@Component
public class MovieFieldMapper implements FieldSetMapper<Movie> {

	@Override
	public Movie mapFieldSet(FieldSet fieldSet) throws BindException {
		final Movie movie = new Movie();
		movie.setYear(fieldSet.readLong(0));
		movie.setTitle(fieldSet.readString(1));
		movie.setStudios(fieldSet.readString(2));
		movie.setProducers(fieldSet.readString(3));
		movie.setWinner(Optional.ofNullable(fieldSet.readString(4)).orElse(""));
		return movie;
	}

}
