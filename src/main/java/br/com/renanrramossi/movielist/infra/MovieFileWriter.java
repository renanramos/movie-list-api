package br.com.renanrramossi.movielist.infra;

import static br.com.renanrramossi.movielist.common.MovieConstants.SQL_COMMAND;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.stereotype.Component;

import br.com.renanrramossi.movielist.model.Movie;

@Component
public class MovieFileWriter extends JdbcBatchItemWriter<Movie> {

	public MovieFileWriter(final DataSource dataSource) {
		this.setDataSource(dataSource);
		this.setSql(SQL_COMMAND);
	    this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
	    this.afterPropertiesSet();
	}
}
