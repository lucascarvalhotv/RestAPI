package com.furb.rest.RestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.furb.rest.RestAPI.model.Instituicao;
import com.furb.rest.RestAPI.model.Obra;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {

}