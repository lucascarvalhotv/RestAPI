package com.furb.rest.RestAPI.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.furb.rest.RestAPI.exception.InstituicaoNotFoundException;
import com.furb.rest.RestAPI.exception.ObrasNotFoundException;
import com.furb.rest.RestAPI.model.Instituicao;
import com.furb.rest.RestAPI.model.Obra;
import com.furb.rest.RestAPI.model.ObraDelete;
import com.furb.rest.RestAPI.model.ObraPost;
import com.furb.rest.RestAPI.model.ObraPut;
import com.furb.rest.RestAPI.repository.InstituicaoRepository;
import com.furb.rest.RestAPI.repository.ObrasRepository;

@RestController
public class ObrasController {

	@Autowired
	ObrasRepository obrasRepository;
	@Autowired
	InstituicaoController instituicaoController;

	/**
	 * Consulta todas as obras existentes
	 * 
	 * @return lista com as obras encontradas
	 */
	@GetMapping("/obras")
	public List<Obra> getAllObras() {
		return obrasRepository.findAll();
	}

	/**
	 * Cria uma nova obra
	 * 
	 * @param obra dados da obra a ser criada
	 * @return dados da obra criada em um arquivo JSON
	 * @throws InstituicaoNotFoundException 
	 */
	@PostMapping("/obras")
	public ResponseEntity<?> createObra(@Valid @RequestBody ObraPost obraPost) throws InstituicaoNotFoundException {
		if (obraPost.getIdInstituicao() == null) {
			return ResponseEntity.badRequest().body("É necessário informar o id da instituição");
		}
		
		Instituicao instituicao = instituicaoController.getInstituicaoById(obraPost.getIdInstituicao());
		
		if (instituicao == null) {
			return ResponseEntity.badRequest().body("Instituicao não encontrada com o id:" + obraPost.getIdInstituicao());
		}
		
		Obra obra = new Obra(obraPost, instituicao);
		
		return ResponseEntity.ok().body(obrasRepository.save(obra));
	}

	/**
	 * Consulta uma obra a partir de um id. Se nenhuma obra for encontrada, retorna
	 * a exceção {@link ObrasNotFoundException}.
	 * 
	 * @param obraId id da obra a ser consultada
	 * @return dados da obra consultada
	 * @throws ObrasNotFoundException
	 */
	@GetMapping("/obras/{id}")
	public Obra getObraById(@PathVariable(value = "id") Long obraId) throws ObrasNotFoundException {
		return obrasRepository.findById(obraId).orElseThrow(() -> new ObrasNotFoundException(obraId));
	}

	/**
	 * Atualiza uma obra a partir do seu id. Não é necessário informar todos os
	 * campos da obra, apenas aqueles que querem ser atualizados. Se não for
	 * encontrada uma obra com o id informado será retornada a exceção
	 * {@link ObrasNotFoundException}.
	 * 
	 * @param obraId      id a obra a ser atualizada
	 * @param obraDetails dados da obra a serem atualizados
	 * @return todos os dados da obra já atualizada
	 * @throws ObrasNotFoundException
	 */
	@PutMapping("/obras/{id}")
	public Obra updateObras(@PathVariable(value = "id") Long obraId, @Valid @RequestBody ObraPut obraDetails)
			throws ObrasNotFoundException {

		Obra obra = obrasRepository.findById(obraId).orElseThrow(() -> new ObrasNotFoundException(obraId));
		
		System.out.println(obraDetails);
		
		if (obraDetails.getAutor() != null && !obraDetails.getAutor().isEmpty()) 
			obra.setAutor(obraDetails.getAutor());
		
		if (obraDetails.getTitulo() != null && !obraDetails.getTitulo().isEmpty())
			obra.setTitulo(obraDetails.getTitulo());
		
		if (obraDetails.getAno() != null && !obraDetails.getAno().isEmpty())
			obra.setAno(obraDetails.getAno());
		
		if (obraDetails.getEdicao() != null && !obraDetails.getEdicao().isEmpty())
			obra.setEdicao(obraDetails.getEdicao());
		
		if (obraDetails.getLocal() != null && !obraDetails.getLocal().isEmpty())
			obra.setLocal(obraDetails.getLocal());
		
		if (obraDetails.getEditora() != null && !obraDetails.getEditora().isEmpty())
			obra.setEditora(obraDetails.getEditora());
		
		if (obraDetails.getPaginas() != null && !obraDetails.getPaginas().isEmpty())
			obra.setPaginas(obraDetails.getPaginas());
		
		if (obraDetails.getIsbn() != null && !obraDetails.getIsbn().isEmpty())
			obra.setIsbn(obraDetails.getIsbn());
		
		if (obraDetails.getIssn() != null && !obraDetails.getIssn().isEmpty())
			obra.setIssn(obraDetails.getIssn());

		Obra obraAtualizada = obrasRepository.save(obra);

		return obraAtualizada;
	}

	/**
	 * Remova uma obra a partir do seu id.
	 * 
	 * @param obraId id da obra ser removida
	 * @return mensagem com o resultado da exclusão da obra
	 * @throws ObrasNotFoundException
	 */
	@DeleteMapping("/obras/{id}")
	public ResponseEntity<?> deleteObras(@PathVariable(value = "id") Long obraId) throws ObrasNotFoundException {
		Obra obras = obrasRepository.findById(obraId).orElseThrow(() -> new ObrasNotFoundException(obraId));

		obrasRepository.delete(obras);

		return ResponseEntity.ok().body("{\"success\":{\"text\":\"obra removida\"}}");
	}

	/**
	 * Remove todas as obras a partir do seu título
	 * @param obra titulo das obras a serem removidas
	 * @return mensagem co o resultado da exclusão da obra
	 */
	@DeleteMapping("/obras")
	public ResponseEntity<?> deleteObras(@Valid @RequestBody ObraDelete obraARemover) {
		Obra obra = new Obra();
		obra.setTitulo(obraARemover.getTitulo());
		
		Example<Obra> obraExample = Example.of(obra, ExampleMatcher.matchingAny());
		List<Obra> obrasEncontradas = obrasRepository.findAll(obraExample);

		obrasRepository.deleteAll(obrasEncontradas);

		return ResponseEntity.ok().body("{\"success\":{\"text\":\"obra(s) removida\"}}");
	}
	
	public List<Obra> getAllByInstitucao(Long idInstituicao) {
		Instituicao instituicao = new Instituicao();
		instituicao.setId(idInstituicao);
		
		Obra obra = new Obra();
		obra.setInstituicao(instituicao);
		
		Example<Obra> obraExample = Example.of(obra, ExampleMatcher.matchingAny());
		List<Obra> obrasEncontradas = obrasRepository.findAll(obraExample);

		return obrasEncontradas;
	}
}