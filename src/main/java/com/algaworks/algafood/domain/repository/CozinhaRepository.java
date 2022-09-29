package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {
	/*
	 * Para fins didáticos... é possível criar consultas personalizadas utilizando a
	 * immplementação dinâmica do jpa, seguindo os seguintes critérios...
	 * 
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query
	 * -methods.query-creation
	 * 
	 */

	// Retorna uma lista de cozinhas buscando por "nome", utilizando o nome do
	// atributo para uma
	// busca exata e retornando uma lista de cozinhas
	Optional<Cozinha> nome(String nome);

	/*
	 * Busca similar ao criterio like, prefixado "fidby" atributo
	 */
	List<Cozinha> findBynomeLike(String nome);

	/*
	 * Buscar lista por nome com case insensitive
	 * https://www.baeldung.com/spring-data-case-insensitive-queries
	 */
	List<Cozinha> findByNomeLikeIgnoreCase(String nome);

	// @Query("from cozinha where nome like %:nome%")
	List<Cozinha> buscarListaPorNome(String nome);

}
