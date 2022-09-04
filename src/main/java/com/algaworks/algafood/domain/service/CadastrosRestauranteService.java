package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastrosRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	public Restaurante salvar(Restaurante restaurante) {

		return restauranteRepository.salvar(restaurante);

	}
}
