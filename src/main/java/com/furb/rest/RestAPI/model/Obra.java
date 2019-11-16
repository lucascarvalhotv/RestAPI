package com.furb.rest.RestAPI.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "obras")
public class Obra {

	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	private String autor;
	@NotBlank
	private String titulo;
	@NotBlank
	private String ano;
	@NotBlank
	private String edicao;
	@NotBlank
	private String local;
	@NotBlank
	private String editora;
	@NotBlank
	private String paginas;
	@NotBlank
	private String isbn;
	@NotBlank
	private String issn;

	public Obra() {
		super();
	}

	public Obra(Long id, @NotBlank String autor, @NotBlank String titulo, @NotBlank String ano,
			@NotBlank String edicao, @NotBlank String local, @NotBlank String editora, @NotBlank String paginas,
			@NotBlank String isbn, @NotBlank String issn) {
		super();
		this.id = id;
		this.autor = autor;
		this.titulo = titulo;
		this.ano = ano;
		this.edicao = edicao;
		this.local = local;
		this.editora = editora;
		this.paginas = paginas;
		this.isbn = isbn;
		this.issn = issn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

}