package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha salvar(Cozinha cozinha) {

		/*
		 * Lógica de negócio aqui...
		 * 
		 */

		return cozinhaRepository.salvar(cozinha);
	}

	public void remover(Long cozinhaId) {

		try {
			cozinhaRepository.remover(cozinhaId);
		} catch (EmptyResultDataAccessException e) {
			/*
			 * exception de negócio
			 */
			throw new EntidadeNaoEncontradaException(
					String.format("Não exite um cadastro de cozinha com o código %d", cozinhaId));

		} catch (DataIntegrityViolationException e) {
			/*
			 * exception de negócio
			 */
			throw new EntidadeEmUsoException(
					String.format("Cozinha de código %d não pode ser removida, pois está em uso.", cozinhaId));
		}
	}

}
