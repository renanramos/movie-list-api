package br.com.renanrramossi.movielist.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, unique = true)
	private Long id;

	@Column
	private Long year;

	@Column
	private String title;

	@Column
	private String studios;

	@Column
	private String producers;

	@Column
	private String winner;

	@Override
	public int hashCode() {
		return Objects.hash(id, producers);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		Movie movie = (Movie) obj;

		return movie.getId() != id && movie.getProducers().contains(producers);
	}
}
