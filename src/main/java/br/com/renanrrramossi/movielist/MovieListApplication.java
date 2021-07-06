package br.com.renanrrramossi.movielist;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.renanrrramossi.movielist.infra.MovieDataProcessor;
import br.com.renanrrramossi.movielist.infra.MovieFieldMapper;
import br.com.renanrrramossi.movielist.infra.MovieFileReader;
import br.com.renanrrramossi.movielist.infra.MovieFileWriter;
import br.com.renanrrramossi.movielist.model.Movie;

@SpringBootApplication
@EnableBatchProcessing
public class MovieListApplication {

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	public static void main(String[] args) {
		SpringApplication.run(MovieListApplication.class, args);
	}

	@Bean("movieJob")
	public Job initialize(final Step movieStep) {
		return jobs.get("readMovies")
				.incrementer(new RunIdIncrementer())
				.flow(movieStep)
				.end()
				.build();
	}

	@Bean("movieStep")
	public Step buildMoviesStep(final MovieFileReader movieFileReader, final MovieFieldMapper mapper,
			final MovieDataProcessor movieProcessor,
			final MovieFileWriter movieFileWriter) {
		return stepBuilderFactory.get("movieStep")
				.<Movie, Movie>chunk(10)
				.reader(movieFileReader)
				.processor(movieProcessor)
				.writer(movieFileWriter)
				.build();
	}
}
