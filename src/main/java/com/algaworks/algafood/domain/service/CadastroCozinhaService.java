package com.algaworks.algafood.domain.service;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CadastroCozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha salvar(Cozinha cozinha) {

		return cozinhaRepository.saveAndFlush(cozinha);
	}

	public void remover(Long cozinhaId) {

		try {
			cozinhaRepository.deleteById(cozinhaId);

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

	public Cozinha merge(Long cozinhaId, Map<String, Object> dadosOrigem) {

		/*
		 * preencher o objeto com dados do banco... criar um ojectmapper para mapear o
		 * que foi passado pela api criar um field para receber o valor com base na
		 * classe origem
		 * 
		 * usar o getfield para atribuir valor ao field com base no objeto convertido
		 * pelo serializador do jackson
		 * 
		 * por fim atualizar o objeto cozinha atualizar com base no resultdo da
		 * conversão
		 * 
		 */
		Cozinha cozinhaAtualizar = buscarCozinha(cozinhaId);

		ObjectMapper objectMapper = new ObjectMapper();
		Cozinha cozinhaOrigem = objectMapper.convertValue(dadosOrigem, Cozinha.class);

		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Cozinha.class, nomePropriedade);
			field.setAccessible(true);

			Object novoValor = ReflectionUtils.getField(field, cozinhaOrigem);

			ReflectionUtils.setField(field, cozinhaAtualizar, novoValor);

		});

		return salvar(cozinhaAtualizar);

	}

	public Cozinha buscarCozinha(Long cozinhaId) {
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> {
			throw new EntidadeNaoEncontradaException(
					String.format("Não exite um cadastro de cozinha com o código %d", cozinhaId));
		});

		return cozinha;
	}

}
