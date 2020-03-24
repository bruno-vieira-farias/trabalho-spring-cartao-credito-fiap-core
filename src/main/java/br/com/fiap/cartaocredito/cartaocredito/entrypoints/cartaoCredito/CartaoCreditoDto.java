package br.com.fiap.cartaocredito.cartaocredito.entrypoints.cartaoCredito;

public class CartaoCreditoDto {
    private Long rmAluno;
    private Long numero;
    private Long digitoVerificador;

    public CartaoCreditoDto(Long rmAluno, Long numero, Long digitoVerificador) {
        this.rmAluno = rmAluno;
        this.numero = numero;
        this.digitoVerificador = digitoVerificador;
    }

    public Long getRmAluno() {
        return rmAluno;
    }

    public void setRmAluno(Long rmAluno) {
        this.rmAluno = rmAluno;
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
