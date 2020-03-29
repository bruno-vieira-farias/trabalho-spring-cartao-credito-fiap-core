package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class GeradorCartaoCreditoService {
    private final Long numeroCartaoInicial = 4000000000000000L;
    private Long proximoNumeroCartao;
    private Random gerador = new Random();

    public GeradorCartaoCreditoService() {
        this.proximoNumeroCartao = this.numeroCartaoInicial;
    }

    public CartaoCreditoDto obtemCartaoNovo() {
        CartaoCreditoDto cartaoCreditoDto = new CartaoCreditoDto(
                this.proximoNumeroCartao,
                geraCvc(),
                LocalDate.now().plusYears(5)
        );

        this.proximoNumeroCartao += 1;
        return cartaoCreditoDto;
    }

    private int geraCvc() {
        return gerador.nextInt((999 - 100) + 1) + 100;
    }
}
