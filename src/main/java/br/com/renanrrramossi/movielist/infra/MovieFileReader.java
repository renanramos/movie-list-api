package br.com.renanrrramossi.movielist.infra;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import br.com.renanrrramossi.movielist.model.Movie;

@Component
public class MovieFileReader extends FlatFileItemReader<Movie> {

	public MovieFileReader()
	{
		final DefaultLineMapper<Movie> defaultLineMapper = new DefaultLineMapper<>();

		final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(";");
		lineTokenizer.setStrict(false);

		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(new MovieFieldMapper());
		this.setLineMapper(defaultLineMapper);
		this.setLinesToSkip(1);
		this.setResource(new ClassPathResource("data/movielist.csv"));
	}
}
