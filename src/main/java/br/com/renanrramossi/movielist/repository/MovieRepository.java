package br.com.renanrramossi.movielist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renanrramossi.movielist.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
