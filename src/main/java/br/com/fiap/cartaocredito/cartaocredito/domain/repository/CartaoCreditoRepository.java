package br.com.fiap.cartaocredito.cartaocredito.domain.repository;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCreditoPk;
import org.springframework.data.repository.CrudRepository;

public interface CartaoCreditoRepository extends CrudRepository<CartaoCredito, CartaoCreditoPk> {

    CartaoCredito findByTitular_Rm(Long rm);

}
