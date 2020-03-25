package br.com.fiap.cartaocredito.cartaocredito.entrypoints.transacao;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class TransacaoDto {
    private Integer id;
    private ZonedDateTime dataHoraCriacao;
    private BigDecimal valor;
    private StatusTransacaoDto status;
    private Long numeroCartao;
    private Long digitoCartao;
    private String codigoAutorizacao;

    public TransacaoDto() {
    }

    public TransacaoDto(Integer id, ZonedDateTime dataHoraCriacao, BigDecimal valor, StatusTransacaoDto status, Long numeroCartao, Long digitoCartao, String codigoAutorizacao) {
        this.id = id;
        this.dataHoraCriacao = dataHoraCriacao;
        this.valor = valor;
        this.status = status;
        this.numeroCartao = numeroCartao;
        this.digitoCartao = digitoCartao;
        this.codigoAutorizacao = codigoAutorizacao;
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

    public Long getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(Long numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Long getDigitoCartao() {
        return digitoCartao;
    }

    public void setDigitoCartao(Long digitoCartao) {
        this.digitoCartao = digitoCartao;
    }

    public String getCodigoAutorizacao() {
        return codigoAutorizacao;
    }

    public void setCodigoAutorizacao(String codigoAutorizacao) {
        this.codigoAutorizacao = codigoAutorizacao;
    }
}