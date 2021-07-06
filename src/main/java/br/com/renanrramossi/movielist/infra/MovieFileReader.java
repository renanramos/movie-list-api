package br.com.renanrramossi.movielist.infra;

import static br.com.renanrramossi.movielist.common.MovieConstants.DATA_MOVIELIST_CSV_FILE_PATH;
import static br.com.renanrramossi.movielist.common.MovieConstants.DELIMITER_CSV_FILE;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import br.com.renanrramossi.movielist.model.Movie;

@Component
public class MovieFileReader extends FlatFileItemReader<Movie> {

	public MovieFileReader()
	{
		final DefaultLineMapper<Movie> defaultLineMapper = new DefaultLineMapper<>();

		defaultLineMapper.setLineTokenizer(getDelimitedLineTokenizer());
		defaultLineMapper.setFieldSetMapper(new MovieFieldMapper());

		this.setLineMapper(defaultLineMapper);
		this.setLinesToSkip(1);
		this.setResource(new ClassPathResource(DATA_MOVIELIST_CSV_FILE_PATH));
	}

	private DelimitedLineTokenizer getDelimitedLineTokenizer() {
		final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(DELIMITER_CSV_FILE);
		lineTokenizer.setStrict(false);
		return lineTokenizer;
	}
}
