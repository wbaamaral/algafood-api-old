package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.model.Cidade;

public interface CidadeRepository {

	List<Cidade> listar();

	Optional<Cidade> buscar(Long id);

	Cidade salvar(Cidade cidade);

	void remover(Cidade cidade);

}
