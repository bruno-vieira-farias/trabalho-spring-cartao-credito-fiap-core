package br.com.fiap.cartaocredito.cartaocredito.entrypoints.transacao;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class TransacaoDto {
    private Integer id;
    private ZonedDateTime dataHoraCriacao;
    private BigDecimal valor;
    private StatusTransacaoDto status;
    private String codigoAutorizacao;
    private Long numeroCartao;

    public TransacaoDto(Integer id, ZonedDateTime dataHoraCriacao, BigDecimal valor, StatusTransacaoDto status, String codigoAutorizacao, Long numeroCartao) {
        this.id = id;
        this.dataHoraCriacao = dataHoraCriacao;
        this.valor = valor;
        this.status = status;
        this.codigoAutorizacao = codigoAutorizacao;
        this.numeroCartao = numeroCartao;
    }

    public Integer getId() {
        return id;
    }

    public ZonedDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public StatusTransacaoDto getStatus() {
        return status;
    }

    public String getCodigoAutorizacao() {
        return codigoAutorizacao;
    }

    public Long getNumeroCartao() {
        return numeroCartao;
    }

    @Override
    public String toString() {
        return " Id: " + getId() +
                "; Horário da transação: " + getDataHoraCriacao() +
                "; Valor: " + getValor() +
                "; Status: " + getStatus() +
                "; Código de Autorização: " + getCodigoAutorizacao();
    }
}