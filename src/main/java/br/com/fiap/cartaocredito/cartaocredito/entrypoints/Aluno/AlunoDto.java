package br.com.fiap.cartaocredito.cartaocredito.entrypoints.Aluno;

public class AlunoDto {
    private Long rm;
    private String nome;
    private Long numeroCartao;
    private Long digitoCartao;

    public AlunoDto(Long rm, String nome, Long numeroCartao, Long digitoCartao) {
        this.rm = rm;
        this.nome = nome;
        this.numeroCartao = numeroCartao;
        this.digitoCartao = digitoCartao;
    }

    public Long getRm() {
        return rm;
    }

    public void setRm(Long rm) {
        this.rm = rm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(Long numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Long getDigitoCartao() {
        return digitoCartao;
    }

    public void setDigitoCartao(Long digitoCartao) {
        this.digitoCartao = digitoCartao;
    }
}
