package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.RecursoInesistenteException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastrosRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastrosRestauranteService cadastroRestaurante;

	@GetMapping
	public List<Restaurante> listar() {

		return restauranteRepository.listar();
	}

	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteRepository.buscar(restauranteId);

		if (restaurante != null) {
			return ResponseEntity.ok(restaurante);
		} else
			return ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			Restaurante restauranteSalvar = cadastroRestaurante.salvar(restaurante);

			return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvar);

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
		try {

			Restaurante restauranteAtual = cadastroRestaurante.atualizar(restaurante, restauranteId);

			return ResponseEntity.ok(restauranteAtual);

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		} catch (RecursoInesistenteException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
			@RequestBody Map<String, Object> dadosOrigem) {

		Restaurante restaurante = cadastroRestaurante.merge(restauranteId, dadosOrigem);
		
		if (restaurante != null)
			return ResponseEntity.ok(restaurante);
		else
			return ResponseEntity.notFound().build();
	}
}
