package br.com.fiap.cartaocredito.cartaocredito.domain.repository;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Transacao;
import org.springframework.data.repository.CrudRepository;

public interface TransacaoRepository extends CrudRepository<Transacao, Integer> {

    Transacao findByCartao(CartaoCredito cartao);
}
