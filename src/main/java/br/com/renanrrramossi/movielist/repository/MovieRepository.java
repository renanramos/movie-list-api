package br.com.renanrrramossi.movielist.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.renanrrramossi.movielist.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, UUID> {

}
