package br.com.fiap.cartaocredito.cartaocredito.domain.arquivoImportacao;

/**
 * Classe que representa cada linha do arquivo importado.
 */
public class Linha {
    private String nome;
    private Long rm;
    private Long numeroCartao;
    private Long digitoCartao;

    public Linha(String nome, Long rm, Long numeroCartao, Long digitoCartaoAluno) {
        this.nome = nome;
        this.rm = rm;
        this.numeroCartao = numeroCartao;
        this.digitoCartao = digitoCartaoAluno;
    }

    public String getNome() {
        return nome;
    }

    public Long getRm() {
        return rm;
    }

    public Long getNumeroCartao() {
        return numeroCartao;
    }

    public Long getDigitoCartao() {
        return digitoCartao;
    }
}
