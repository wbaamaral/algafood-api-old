package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public class RestauranteRepositoryImpl {

	@PersistenceContext
	private EntityManager manager;

	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		var parametros = new HashMap<String, Object>();

		var jpql = new StringBuilder();

		jpql.append("from Restaurante where 0 = 0 ");

		if (StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}

		if (taxaFreteInicial != null) {
			jpql.append("and taxaFrete >= :taxaFreteInicial ");
			parametros.put("taxaFreteInicial", taxaFreteInicial);
		}

		if (taxaFreteFinal != null) {
			jpql.append("and taxaFrete <= :taxaFreteFinal ");
			parametros.put("taxaFreteFinal", taxaFreteFinal);
		}

		TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);

		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

		return query.getResultList();

	}

	public List<Restaurante> criteriaSimples(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

		var builder = manager.getCriteriaBuilder();
		var criteria = builder.createQuery(Restaurante.class);
		var predicates = new ArrayList<Predicate>();

		var root = criteria.from(Restaurante.class);

		if (StringUtils.hasText(nome))
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));

		if (taxaFreteInicial != null)
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));

		if (taxaFreteFinal != null)
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));

		criteria.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Restaurante> query = manager.createQuery(criteria);

		return query.getResultList();
	}
}
