package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
	/*
	 * Para fins didáticos...
	 * é possível criar consultas personalizadas utilizando a immplementação
	 * dinâmica do jpa, seguindo os seguintes critérios...
	 * 
	 */
	
	//Retorna uma lista de cozinhas buscando por "nome", utilizando o nome do atributo para uma 
	//busca exata e retornando uma lista de cozinhas
	List<Cozinha> nome(String nome);
	
	/*
	 * Busca similar ao criterio like, prefixado "fidby" atributo
	 */
	List<Cozinha> findbyNome(String nome);
}
