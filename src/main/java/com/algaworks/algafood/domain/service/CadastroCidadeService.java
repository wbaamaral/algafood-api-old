package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	public Cidade salvar(Cidade cidadeNova) {

		Cidade cidade = cidadeRepository.salvar(cidadeNova);

		return cidade;
	}

	public Cidade atualizar(Cidade cidadeParam, Long cidadeId) {

		Cidade cidade = cidadeRepository.buscar(cidadeId).orElseThrow(() -> {
			throw new EntidadeNaoEncontradaException(String.format("Não existe cidade com o código %d.", cidadeId));
		});

		BeanUtils.copyProperties(cidade, cidadeParam);

		return cidadeRepository.salvar(cidadeParam);
	}

	public void remover(Long cidadeId) {
		try {
			Cidade cidade = cidadeRepository.buscar(cidadeId).orElseThrow(() -> {
				throw new EntidadeNaoEncontradaException(String.format("Não existe cidade com o código %d.", cidadeId));
			});

			cidadeRepository.remover(cidade);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cidade de código %d não pode ser removida, pois está em uso.", cidadeId));
		}
	}

}
