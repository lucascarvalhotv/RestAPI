package com.furb.rest.RestAPI.exception;

public class InstituicaoNotFoundException extends Exception {
	public InstituicaoNotFoundException(long idInstituicao) {
		super(String.format("Instituicao não encontrada com o id: '%s'", idInstituicao));
	}
}