package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstado;

	@GetMapping
	public List<Estado> listar() {

		return estadoRepository.findAll();

	}

	@GetMapping(value = ("/{estadoId}"))
	public ResponseEntity<?> buscarEstado(@PathVariable Long estadoId) {

		Optional<Estado> estado = estadoRepository.findById(estadoId);

		if (estado.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(estado.get());
		} else
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(String.format("Não exite um cadastro de estado com o código %d", estadoId));
	}

	@PutMapping(value = ("/{estadoId}"))
	public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @RequestBody Estado estadoAtualizar) {

		try {

			Estado estado = cadastroEstado.salvar(estadoAtualizar, estadoId);

			return ResponseEntity.status(HttpStatus.OK).body(estado);

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}

	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId) {
		try {
			cadastroEstado.remover(estadoId);

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).build();

	}

	@PostMapping
	public ResponseEntity<?> incluir(@RequestBody Estado estadoNovo) {

		Estado estado = cadastroEstado.incluir(estadoNovo);
		return ResponseEntity.status(HttpStatus.CREATED).body(estado);
	}
}
