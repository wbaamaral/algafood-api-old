package com.algaworks.algafood.api.controller;

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
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@GetMapping
	public ResponseEntity<?> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(cidadeRepository.listar());
	}

	@GetMapping("/{cidadeId}")
	public ResponseEntity<?> listar(@PathVariable Long cidadeId) {
		Cidade cidade = cidadeRepository.buscar(cidadeId);
		if (cidade != null)
			return ResponseEntity.status(HttpStatus.OK).body(cidade);
		else
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(String.format("Cidade com código %d não existe.", cidadeId));
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cidade cidadeNova) {
		try {
			Cidade cidadeSalvar = cadastroCidade.salvar(cidadeNova);

			return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalvar);

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@PutMapping(value = "/{cidadeId}")
	public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidadeAtualizar) {

		try {
			Cidade cidade = cadastroCidade.atualizar(cidadeAtualizar, cidadeId);

			return ResponseEntity.status(HttpStatus.OK).body(cidade);

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<?> remover(@PathVariable Long cidadeId) {
		try {
			cadastroCidade.remover(cidadeId);
			return ResponseEntity.ok().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
