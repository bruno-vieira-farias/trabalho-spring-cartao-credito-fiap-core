package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import java.time.LocalDate;
import java.util.Objects;

public class CartaoCreditoDto {
    private Long numero;
    private Integer cvc;
    private LocalDate vencimento;

    public CartaoCreditoDto(Long numero, Integer cvc, LocalDate vencimento) {
        this.numero = numero;
        this.cvc = cvc;
        this.vencimento = vencimento;
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
        CartaoCreditoDto that = (CartaoCreditoDto) o;
        return Objects.equals(numero, that.numero) &&
                Objects.equals(cvc, that.cvc) &&
                Objects.equals(vencimento, that.vencimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, cvc, vencimento);
    }
}
