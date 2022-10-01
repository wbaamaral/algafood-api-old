package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries,
		JpaSpecificationExecutor<Restaurante> {

	/*
	 * Select gerado pelo jpa
	 * 
	 * select restaurant0_.id as id1_8_0_, cozinha1_.id as id1_1_1_, formapagam3_.id
	 * as id1_3_2_, restaurant0_.cozinha_id as cozinha11_8_0_,
	 * restaurant0_.data_atualizacao as data_atu2_8_0_, restaurant0_.data_cadastro
	 * as data_cad3_8_0_, restaurant0_.endereco_bairro as endereco4_8_0_,
	 * restaurant0_.endereco_cep as endereco5_8_0_, restaurant0_.endereco_cidade_id
	 * as enderec12_8_0_, restaurant0_.endereco_complemento as endereco6_8_0_, res
	 * taurant0_.endereco_logradouro as endereco7_8_0_, restaurant0_.endereco_numero
	 * as endereco8_8_0_, restaurant0_.nome as nome9_8_0_, restaurant0_.taxa_frete
	 * as taxa_fr10_8_0_, cozinha1_.nome as nome2_1_1_, formapagam3_.desc ricao as
	 * descrica2_3_2_, formaspaga2_.restaurante_id as restaura1_9_0__,
	 * formaspaga2_.forma_pagamento_id as forma_pa2_9_0__ from restaurante
	 * restaurant0_ inner join cozinha cozinha1_ on
	 * restaurant0_.cozinha_id=cozinha1_. id left outer join
	 * restaurante_forma_pagamento formaspaga2_ on
	 * restaurant0_.id=formaspaga2_.restaurante_id left outer join forma_pagamento
	 * formapagam3_ on formaspaga2_.forma_pagamento_id=formapagam3_.id
	 * 
	 */
	@Query("from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento")
	List<Restaurante> findAll();

	List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

	List<Restaurante> findTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long cozinha);

}
