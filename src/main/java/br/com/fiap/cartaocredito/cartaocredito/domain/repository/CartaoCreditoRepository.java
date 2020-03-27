package br.com.fiap.cartaocredito.cartaocredito.domain.repository;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import org.springframework.data.repository.CrudRepository;

public interface CartaoCreditoRepository extends CrudRepository<CartaoCredito,Long> {
}
