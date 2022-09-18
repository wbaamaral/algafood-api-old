package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/testes")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@GetMapping("/cozinhas/list-por-nome")
	public List<Cozinha> listarCozinhasPorNome(String nome) {

		return cozinhaRepository.findBynome(nome);

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
}
