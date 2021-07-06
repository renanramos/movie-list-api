package br.com.renanrramossi.movielist.controller.rest;

import static br.com.renanrramossi.movielist.controller.rest.CommonTestUtil.MAX_RESPONSE_KEY;
import static br.com.renanrramossi.movielist.controller.rest.CommonTestUtil.MIN_RESPONSE_KEY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.renanrramossi.movielist.model.ProducerStatisticDTO;
import br.com.renanrramossi.movielist.service.MovieService;
import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProducerControllerTest {

	@MockBean
	private MovieService movieService;

	@Autowired
	private TestRestTemplate restTemplate;

	private ProducerStatisticDTO producersMinInterval;
	private ProducerStatisticDTO producersMaxInterval;

	@SuppressWarnings("unchecked")
	@Test
	void findAll_withValuesFromBackEnd_shouldReturnAMapWithListOfProducers() {

		setup();

		when(movieService.findAll()).thenReturn(generateMapOfProducers());

		final Map<String, ProducerStatisticDTO> response = restTemplate.getForObject("/api/producers/",
				Map.class);

		assertThat(response).isNotNull();

		final ProducerStatisticDTO min = convertDataResponseToList(response, MIN_RESPONSE_KEY);

		assertListValues(min, producersMinInterval);

		final ProducerStatisticDTO max = convertDataResponseToList(response, MAX_RESPONSE_KEY);

		assertListValues(max, producersMaxInterval);
	}

	@SneakyThrows
	private ProducerStatisticDTO convertDataResponseToList(final Map<String, ProducerStatisticDTO> response,
			final String key) {
		final ObjectMapper mapper = new ObjectMapper();
		final byte[] json = mapper.writeValueAsBytes(response.get(key));
		return mapper.readValue(json, ProducerStatisticDTO.class);
	}

	private void assertListValues(final ProducerStatisticDTO producers,
			final ProducerStatisticDTO expectedProducersList) {
		assertThat(producers).isNotNull();
	}

	private Map<String, ProducerStatisticDTO> generateMapOfProducers() {

		final Map<String, ProducerStatisticDTO> response = new HashMap<>();

		response.put(MIN_RESPONSE_KEY, producersMinInterval);
		response.put(MAX_RESPONSE_KEY, producersMaxInterval);

		return response;
	}

	private ProducerStatisticDTO listOfProducersWithMaxInterval() {
		return ProducerStatisticDTO
							.builder()
							.interval(10L)
							.previousWin(1998L)
							.followingWin(2008L)
							.producer("Producer 3")
				.build();
	}

	private ProducerStatisticDTO listOfProducersWithMinInterval() {

		return ProducerStatisticDTO
								.builder()
								.interval(1L)
								.previousWin(1999L)
								.followingWin(2000L)
								.producer("Producer 1")
				.build();
	}

	private void setup() {
		producersMinInterval = listOfProducersWithMinInterval();
		producersMaxInterval = listOfProducersWithMaxInterval();
	}

}
