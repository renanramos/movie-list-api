package br.com.renanrramossi.movielist.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProducerStatisticDTO {

	private String producer;

	private Long interval;

	private Long previousWin;

	private Long followingWin;

	public ProducerStatisticDTO() {

	}

	private ProducerStatisticDTO(String producer, Long interval, Long previousWin, Long followingWin) {
		this.producer = producer;
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

}
