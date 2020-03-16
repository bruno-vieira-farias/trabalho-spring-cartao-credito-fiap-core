package br.com.fiap.cartaocredito.cartaocredito.entrypoints.transacao;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class TransacaoDto {
    private ZonedDateTime dataHoraCriacao;
    private Long numeroCartao;
    private Long digitoCartao;
    private BigDecimal valor;
    private String status; //Autorizada, Nao_Autorizada, Estornada
}
