package br.com.fiap.cartaocredito.cartaocredito.domain.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CartaoCreditoPk implements Serializable {
    private Long numero;
    private Long digitoVerificador;

    public CartaoCreditoPk() {
    }

    public CartaoCreditoPk(Long numero, Long digitoVerificador) {
        this.numero = numero;
        this.digitoVerificador = digitoVerificador;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(Long digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }
}
