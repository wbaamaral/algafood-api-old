package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/testes")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/list-por-nome")
	public List<Cozinha> listarCozinhasPorNome(String nome) {

		return cozinhaRepository.findBynomeLike('%' + nome + '%');

	}

	@GetMapping("/cozinhas/por-nome")
	public ResponseEntity<Cozinha> cozinhasPorNome(String nome) {
		Optional<Cozinha> cozinha = cozinhaRepository.nome(nome);

		if (cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("/cozinhas/nome-insensitive")
	public List<Cozinha> cozinhaPorNomeMaiusculoMinusculo(String nome) {

		return cozinhaRepository.findByNomeLikeIgnoreCase('%' + nome + '%');
	}

	@GetMapping("/cozinhas/query-cozinhas-por-nome")
	public List<Cozinha> buscarPorNome(String nome) {
		
		return cozinhaRepository.buscarListaPorNome(nome);
	}
	
	@GetMapping("/restaurantes/find")
	public List<Restaurante> buscar(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
}
