package br.com.renanrrramossi.movielist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
}
