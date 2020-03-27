package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GeradorCartaoCreditoService {
    private final Long numeroCartaoInicial = 4000000000000000L;
    private Long proximoNumeroCartao;

    public GeradorCartaoCreditoService() {
        this.proximoNumeroCartao = this.numeroCartaoInicial;
    }

    public CartaoCreditoDto obtemCartaoNovo() {
        Long numero = this.proximoNumeroCartao;
//        int cvc = new Random().nextInt((999 - 100) + 1) + 100;
        int cvc = 1;
        LocalDate vencimento = LocalDate.now().plusYears(5);

        this.proximoNumeroCartao += 1;

        return new CartaoCreditoDto(numero, cvc, vencimento);
    }
}
