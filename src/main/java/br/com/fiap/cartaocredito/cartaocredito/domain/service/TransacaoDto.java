package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import br.com.fiap.cartaocredito.cartaocredito.entrypoints.transacao.StatusTransacaoDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoDto {
    private Integer id;
    private LocalDateTime dataHoraCriacao;
    private BigDecimal valor;
    private StatusTransacaoDto status;
    private String codigoAutorizacao;
    private Long numeroCartao;

    public TransacaoDto(Integer id, LocalDateTime dataHoraCriacao, BigDecimal valor, StatusTransacaoDto status, String codigoAutorizacao, Long numeroCartao) {
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

    public LocalDateTime getDataHoraCriacao() {
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