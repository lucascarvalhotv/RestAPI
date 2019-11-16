package com.furb.rest.RestAPI.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "obras")
public class ObraDelete {

	@Id
	@GeneratedValue
	private Long id;
	private String titulo;

	public ObraDelete() {
		super();
	}

	public ObraDelete(@NotBlank String titulo) {
		super();
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}