package br.com.renanrramossi.movielist;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.renanrramossi.movielist.infra.MovieFieldMapper;
import br.com.renanrramossi.movielist.infra.MovieFileReader;
import br.com.renanrramossi.movielist.infra.MovieFileWriter;
import br.com.renanrramossi.movielist.model.Movie;

@SpringBootApplication
@EnableBatchProcessing
@EnableAutoConfiguration
public class ProducersListApplication {

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	public static void main(String[] args) {
		SpringApplication.run(ProducersListApplication.class, args);
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
			final MovieFileWriter movieFileWriter) {
		return stepBuilderFactory.get("movieStep")
				.<Movie, Movie>chunk(10)
				.reader(movieFileReader)
				.writer(movieFileWriter)
				.build();
	}
}
