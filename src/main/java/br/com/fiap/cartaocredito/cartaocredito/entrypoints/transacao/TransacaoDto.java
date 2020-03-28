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

    public void setId(Integer id) {
        this.id = id;
    }

    public ZonedDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(ZonedDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public StatusTransacaoDto getStatus() {
        return status;
    }

    public void setStatus(StatusTransacaoDto status) {
        this.status = status;
    }

    public String getCodigoAutorizacao() {
        return codigoAutorizacao;
    }

    public void setCodigoAutorizacao(String codigoAutorizacao) {
        this.codigoAutorizacao = codigoAutorizacao;
    }

    public Long getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(Long numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
}