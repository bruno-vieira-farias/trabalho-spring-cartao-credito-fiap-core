package br.com.fiap.cartaocredito.cartaocredito.domain.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CartaoCredito {
    @Id
    private Long numeroCartao;
    private Long digitoVerificadorCartao;
    @OneToOne
    private Aluno titular;

    public CartaoCredito() {
    }

    public CartaoCredito(Long numeroCartao, Long digitoVerificadorCartao, Aluno titular) {
        this.numeroCartao = numeroCartao;
        this.digitoVerificadorCartao = digitoVerificadorCartao;
        this.titular = titular;
    }

    public Long getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(Long numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Long getDigitoVerificadorCartao() {
        return digitoVerificadorCartao;
    }

    public void setDigitoVerificadorCartao(Long digitoVerificadorCartao) {
        this.digitoVerificadorCartao = digitoVerificadorCartao;
    }

    public Aluno getTitular() {
        return titular;
    }

    public void setTitular(Aluno titular) {
        this.titular = titular;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartaoCredito that = (CartaoCredito) o;
        return Objects.equals(numeroCartao, that.numeroCartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCartao);
    }
}