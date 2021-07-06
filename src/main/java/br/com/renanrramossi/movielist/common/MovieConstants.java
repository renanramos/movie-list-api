package br.com.renanrramossi.movielist.common;

public final class MovieConstants {

	public static final String SQL_COMMAND = "INSERT INTO movie (year, title, studios, producers, winner) "
			+ "VALUES (:year, :title, :studios, :producers, :winner) ";

	public static final String DATA_MOVIELIST_CSV_FILE_PATH = "data/movielist.csv";

	public static final String DELIMITER_CSV_FILE = ";";

	public static final String BASE_PACKAGE = "br.com.renanrramossi.movielist.controller.rest";

	public static final String MIN_RESPONSE_KEY = "min";
	public static final String MAX_RESPONSE_KEY = "max";
}
