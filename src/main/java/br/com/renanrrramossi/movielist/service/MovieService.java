package br.com.renanrrramossi.movielist.service;

import reactor.core.publisher.Flux;

public interface MovieService<T> {

	Flux<T> findAll();

	void initializeDataBase(T e);
}
