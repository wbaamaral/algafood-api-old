package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public Estado salvar(Estado estado, Long estadoId) {
		Estado estadoAtual = estadoRepository.findById(estadoId).orElseThrow(() -> {
			throw new EntidadeNaoEncontradaException(String.format("Estado não encontrado com o código: %d", estadoId));
		});

		BeanUtils.copyProperties(estado, estadoAtual, "id");

		return estadoRepository.save(estadoAtual);

	}

	public boolean remover(Long estadoId) {

		Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);

		if (estadoAtual.isPresent()) {
			try {
				estadoRepository.delete(estadoAtual.get());
				return true;

			} catch (DataIntegrityViolationException e) {

				throw new EntidadeEmUsoException(
						String.format("Estado de código %d não pode ser removido, pois está em uso.", estadoId));
			}

		} else
			throw new EntidadeNaoEncontradaException(String.format("Estado não encontrado com o código: %d", estadoId));
	}

	public Estado incluir(Estado estadoNovo) {

		Estado estado = estadoRepository.save(estadoNovo);

		return estado;

	}
}
