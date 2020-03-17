package br.com.fiap.cartaocredito.cartaocredito.domain.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class CartaoCredito {

    @EmbeddedId
    private CartaoCreditoPk id;

    @OneToOne
    private Aluno titular;

    public CartaoCredito() {
    }

    public CartaoCredito(Long numeroCartao, Long digitoVerificadorCartao, Aluno titular) {
        this.id = new CartaoCreditoPk(numeroCartao,digitoVerificadorCartao);
        this.titular = titular;
    }

    public CartaoCreditoPk getId() {
        return id;
    }

    public void setId(CartaoCreditoPk id) {
        this.id = id;
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
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}