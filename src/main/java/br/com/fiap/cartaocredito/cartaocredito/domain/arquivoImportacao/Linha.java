package br.com.fiap.cartaocredito.cartaocredito.domain.arquivoImportacao;

/**
 * Classe que representa cada linha do arquivo importado.
 */
public class Linha {
    private String nome;
    private Long rm;
    private String codigoTurma;

    public Linha() {
    }

    public Linha(String nome, Long rm, String codigoTurma) {
        this.nome = nome;
        this.rm = rm;
        this.codigoTurma = codigoTurma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getRm() {
        return rm;
    }

    public void setRm(Long rm) {
        this.rm = rm;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }
}
