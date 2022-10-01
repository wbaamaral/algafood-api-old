package com.algaworks.algafood.domain.service;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.RecursoInesistenteException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CadastrosRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	private Restaurante carregarRestaurante(Long restauranteId) {

		Restaurante restaurante = restauranteRepository.findById(restauranteId).orElseThrow(() -> {
			throw new RecursoInesistenteException(
					String.format("Recurso restaurante não existe com esse código %d", restauranteId));

		});

		return restaurante;

	}

	private Cozinha carregarCozinha(Restaurante restaurante) {

		if (restaurante.getCozinha() == null)
			throw new DataIntegrityViolationException("Cozinha não informada, é obrigatório!");

		try {
			return cadastroCozinha.buscarCozinha(restaurante.getCozinha().getId());

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Alteração de conzinha não é possível, não existe cozinha com este código %d",
							restaurante.getCozinha().getId()));

		}
	}

	public Restaurante salvar(Restaurante restaurante) {

		Cozinha cozinha = cadastroCozinha.buscarCozinha(restaurante.getCozinha().getId());

		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cozinha cadastrada com o código %d", restaurante.getCozinha().getId()));
		}

		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);

	}

	public Restaurante atualizar(Restaurante restaurante, Long restauranteId) {

		Cozinha novaCozinha = carregarCozinha(restaurante);

		Restaurante restauranteAtual = carregarRestaurante(restauranteId);

		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
				"produtos");
		restauranteAtual.setCozinha(novaCozinha);

		restauranteRepository.save(restauranteAtual);

		return restauranteAtual;
	}

	public Restaurante merge(Long restauranteId, Map<String, Object> dadosOrigem) {
		// carregar dados originais do restaurnate
		Restaurante restaurante = carregarRestaurante(restauranteId);

		// objeto de transição
		ObjectMapper objectMapper = new ObjectMapper();

		// Converter e carregar valores para restaurante
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

		// loop de atribuição dos valores informados na atualização
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			// mudando modificador de acesso para permitir atribuição de valor
			field.setAccessible(true);

			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			ReflectionUtils.setField(field, restaurante, novoValor);

		});

		return salvar(restaurante);
	}

}
