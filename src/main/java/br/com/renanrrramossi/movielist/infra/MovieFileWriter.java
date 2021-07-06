package br.com.renanrrramossi.movielist.infra;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.stereotype.Component;

import br.com.renanrrramossi.movielist.model.Movie;

@Component
public class MovieFileWriter extends JdbcBatchItemWriter<Movie> {

	private static final String SQL_COMMAND =
			"INSERT INTO movie (year, title, studios, producers, winner) "
					+ "VALUES (:year, :title, :studios, :producers, :winner) ";

	public MovieFileWriter(final DataSource dataSource) {
		this.setDataSource(dataSource);
		this.setSql(SQL_COMMAND);
	    this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
	    this.afterPropertiesSet();
	}
}
