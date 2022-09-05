package com.algaworks.algafood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.RecursoInesistenteException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastrosRestauranteService {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;

	public Restaurante salvar(Restaurante restaurante) {

		Long cozinhaId = restaurante.getCozinha().getId();

		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cozinha cadastrada com o código %d", cozinhaId));
		}
		restaurante.setCozinha(cozinha);

		return restauranteRepository.salvar(restaurante);

	}

	public Restaurante atualizar(Restaurante restaurante, Long restauranteId) {

		if (restaurante.getCozinha() == null || restaurante.getCozinha().getId() == null) {
			throw new DataIntegrityViolationException("Cozinha não informada, é obrigatório!");
		}

		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha novaCozinha;
		try {
			novaCozinha = cozinhaRepository.buscar(cozinhaId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String
					.format("Alteração de conzinha não é possível, não existe cozinha com este código %d", cozinhaId));

		}

		Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);

		if (restauranteAtual != null) {

			if (novaCozinha == null)
				throw new EntidadeNaoEncontradaException(String.format(
						"Alteração de conzinha não é possível, não existe cozinha com este código %d", cozinhaId));

			BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

			restauranteAtual.setCozinha(novaCozinha);

			restauranteRepository.salvar(restauranteAtual);

			return restauranteAtual;
		} else {
			throw new RecursoInesistenteException(
					String.format("Recurso restaurante não existe com esse código %d", restauranteId));

		}
	}
}
