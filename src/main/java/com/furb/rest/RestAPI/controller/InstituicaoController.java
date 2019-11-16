package com.furb.rest.RestAPI.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.furb.rest.RestAPI.exception.InstituicaoNotFoundException;
import com.furb.rest.RestAPI.model.Instituicao;
import com.furb.rest.RestAPI.model.InstituicaoPut;
import com.furb.rest.RestAPI.repository.InstituicaoRepository;

@RestController
public class InstituicaoController {

	@Autowired
	InstituicaoRepository instituicaoRepository;

	/**
	 * Consulta todas as instituições existentes
	 * 
	 * @return lista com as instituições encontradas
	 */
	@GetMapping("/instituicao")
	public List<Instituicao> getAllInstituicoes() {
		return instituicaoRepository.findAll();
	}

	/**
	 * Cria uma nova instituição
	 * 
	 * @param instituicao dados da instituição a ser criada
	 * @return dados da instituição criada em um arquivo JSON
	 */
	@PostMapping("/instituicao")
	public Instituicao createInstituicao(@Valid @RequestBody Instituicao instituicao) {
		return instituicaoRepository.save(instituicao);
	}

	/**
	 * Consulta uma instituição a partir de um id. Se nenhuma instituição for
	 * encontrada, retorna a exceção {@link InstituicaoNotFoundException}.
	 * 
	 * @param idInstituicao id da instituição a ser consultada
	 * @return dados da instituição consultada
	 * @throws InstituicaoNotFoundException
	 */
	@GetMapping("/instituicao/{id}")
	public Instituicao getInstituicaoById(@PathVariable(value = "id") Long idInstituicao)
			throws InstituicaoNotFoundException {
		return instituicaoRepository.findById(idInstituicao)
				.orElseThrow(() -> new InstituicaoNotFoundException(idInstituicao));
	}

	/**
	 * Atualiza uma instituição a partir do seu id. Não é necessário informar todos
	 * os campos da instituição, apenas aqueles que querem ser atualizados. Se não
	 * for encontrada uma instituição com o id informado será retornada a exceção
	 * {@link InstituicoesNotFoundException}.
	 * 
	 * @param isInstituicao      id a instituição ser atualizada
	 * @param instituicaoDetails dados da instituição a serem atualizados
	 * @return todos os dados da instituição já atualizada
	 * @throws InstituicaoNotFoundException
	 */
	@PutMapping("/instituicao/{id}")
	public Instituicao updateInstituicoes(@PathVariable(value = "id") Long idInstituicao,
			@Valid @RequestBody InstituicaoPut instituicaoDetails) throws InstituicaoNotFoundException {

		Instituicao instituicao = instituicaoRepository.findById(idInstituicao)
				.orElseThrow(() -> new InstituicaoNotFoundException(idInstituicao));

		if (instituicaoDetails.getNome() != null && !instituicaoDetails.getNome().isEmpty())
			instituicao.setNome(instituicaoDetails.getNome());

		if (instituicaoDetails.getEntidade() != null && !instituicao.getEntidade().isEmpty())
			instituicao.setEntidade(instituicaoDetails.getEntidade());

		Instituicao instituicaoAtualizada = instituicaoRepository.save(instituicao);

		return instituicaoAtualizada;
	}

	/**
	 * Remova uma instituição a partir do seu id.
	 * 
	 * @param idInstituicao id da instituição ser removida
	 * @return mensagem com o resultado da exclusão da instituição
	 * @throws InstituicaoNotFoundException
	 */
	@DeleteMapping("/instituicao/{id}")
	public ResponseEntity<?> deleteInstituicoes(@PathVariable(value = "id") Long idInstituicao)
			throws InstituicaoNotFoundException {
		Instituicao instituicao = instituicaoRepository.findById(idInstituicao)
				.orElseThrow(() -> new InstituicaoNotFoundException(idInstituicao));

		instituicaoRepository.delete(instituicao);

		return ResponseEntity.ok().body("{\"success\":{\"text\":\"instituição removida\"}}");
	}
}