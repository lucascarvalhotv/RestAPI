package com.furb.rest.RestAPI.exception;

public class ObrasNotFoundException extends Exception {
	private long obraId;

	public ObrasNotFoundException(long obraId) {
		super(String.format("Obra não encontrada com o id: '%s'", obraId));
	}
}