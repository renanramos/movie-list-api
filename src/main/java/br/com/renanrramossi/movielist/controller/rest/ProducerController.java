package br.com.renanrramossi.movielist.controller.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.renanrramossi.movielist.model.ProducerStatisticDTO;
import br.com.renanrramossi.movielist.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "api/producers", produces = "application/json")
@Api(tags = "Producers")
public class ProducerController {

	@Autowired
	private MovieService movieService;

	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get producers")
	public Map<String, ProducerStatisticDTO> findAll() {
		return movieService.findAll();
	}
}
