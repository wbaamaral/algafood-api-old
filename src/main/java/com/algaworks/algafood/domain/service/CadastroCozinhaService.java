package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void remover(Cozinha cozinha) {

		cozinhaRepository.remover(cozinha);

	}
}
