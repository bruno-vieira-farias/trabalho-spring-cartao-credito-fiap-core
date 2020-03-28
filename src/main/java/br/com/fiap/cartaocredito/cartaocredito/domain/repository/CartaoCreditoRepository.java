package br.com.fiap.cartaocredito.cartaocredito.domain.repository;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {

    List<CartaoCredito> findByTitular_Rm(Long idTitular);




}
