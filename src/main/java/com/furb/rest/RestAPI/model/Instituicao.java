package com.furb.rest.RestAPI.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "instituicoes")
public class Instituicao {

	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String entidade;

	public Instituicao() {
		super();
	}
	
	public Instituicao(Long id, @NotBlank String nome, @NotBlank String entidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.entidade = entidade;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEntidade() {
		return entidade;
	}
	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

}