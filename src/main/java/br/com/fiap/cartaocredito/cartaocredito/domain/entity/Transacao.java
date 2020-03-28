package br.com.fiap.cartaocredito.cartaocredito.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id
    private Integer id;
    private LocalDateTime dataHoraCriacao;
    private BigDecimal valor;
    private StatusTransacao status;
    private String codigoAutorizacao;

    @ManyToOne
    private CartaoCredito cartaoCredito;

    public Transacao() {
    }

    public Transacao(Integer id, LocalDateTime dataHoraCriacao, BigDecimal valor, StatusTransacao status, String codigoAutorizacao, CartaoCredito cartaoCredito) {
        this.id = id;
        this.dataHoraCriacao = dataHoraCriacao;
        this.valor = valor;
        this.status = status;
        this.codigoAutorizacao = codigoAutorizacao;
        this.cartaoCredito = cartaoCredito;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public void setStatus(StatusTransacao status) {
        this.status = status;
    }

    public String getCodigoAutorizacao() {
        return codigoAutorizacao;
    }

    public void setCodigoAutorizacao(String codigoAutorizacao) {
        this.codigoAutorizacao = codigoAutorizacao;
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }
}