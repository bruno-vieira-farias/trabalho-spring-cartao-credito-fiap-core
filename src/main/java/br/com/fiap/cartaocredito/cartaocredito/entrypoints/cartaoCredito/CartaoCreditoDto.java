package br.com.fiap.cartaocredito.cartaocredito.entrypoints.cartaoCredito;

import java.time.LocalDate;

public class CartaoCreditoDto {
    private Long idTitular;
    private Long numero;
    private Integer cvc;
    private LocalDate vencimento;

    public CartaoCreditoDto(Long idTitular, Long numero, Integer cvc, LocalDate vencimento) {
        this.idTitular = idTitular;
        this.numero = numero;
        this.cvc = cvc;
        this.vencimento = vencimento;
    }

    public Long getIdTitular() {
        return idTitular;
    }

    public void setIdTitular(Long idTitular) {
        this.idTitular = idTitular;
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
}
