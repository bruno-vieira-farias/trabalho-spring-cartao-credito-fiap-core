package br.com.fiap.cartaocredito.cartaocredito.domain.arquivoImportacao;

/**
 * Classe que representa cada linha do arquivo importado.
 */
public class Linha {
    private String nome;
    private Long rm;
    private String numeroCartao;
    private String digitoCartao;

    public Linha(String nome, Long rm, String numeroCartao, String digitoCartao) {
        this.nome = nome;
        this.rm = rm;
        this.numeroCartao = numeroCartao;
        this.digitoCartao = digitoCartao;
    }

    public String getNome() {
        return nome;
    }

    public Long getRm() {
        return rm;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getDigitoCartao() {
        return digitoCartao;
    }
}
