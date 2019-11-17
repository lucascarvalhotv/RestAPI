package com.furb.rest.RestAPI.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "instituicoes")
public class InstituicaoPut {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String entidade;

	public InstituicaoPut() {
		super();
	};
	
	public InstituicaoPut(Long id, @NotBlank String nome, @NotBlank String entidade) {
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