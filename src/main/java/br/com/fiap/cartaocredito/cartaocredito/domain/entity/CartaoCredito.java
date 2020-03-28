package br.com.fiap.cartaocredito.cartaocredito.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class CartaoCredito {
    @Id
    private Long numero;
    @ManyToOne
    private Aluno titular;
    private Integer cvc;
    private LocalDate vencimento;

    public CartaoCredito() {
    }

    public CartaoCredito(Aluno titular, Long numero, Integer cvc, LocalDate vencimento) {
        this.titular = titular;
        this.numero = numero;
        this.cvc = cvc;
        this.vencimento = vencimento;
    }

    public Aluno getTitular() {
        return titular;
    }

    public void setTitular(Aluno titular) {
        this.titular = titular;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Integer getCvc() {
        return cvc;
    }

    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartaoCredito that = (CartaoCredito) o;
        return Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}
