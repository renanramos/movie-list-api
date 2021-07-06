package br.com.renanrramossi.movielist.service;

import java.util.Map;

import br.com.renanrramossi.movielist.model.ProducerStatisticDTO;

public interface MovieService {

	Map<String, ProducerStatisticDTO> findAll();
}
