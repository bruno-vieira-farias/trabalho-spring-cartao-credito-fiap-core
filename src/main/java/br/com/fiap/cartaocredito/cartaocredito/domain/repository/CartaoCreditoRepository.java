package br.com.fiap.cartaocredito.cartaocredito.domain.repository;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCreditoPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, CartaoCreditoPk> {

    @Query("SELECT c FROM CartaoCredito c  WHERE c.titular.rm = :rm")
    List<CartaoCredito> findByTitular_Rm(Long rm);

}
