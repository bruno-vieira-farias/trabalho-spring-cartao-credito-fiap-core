package br.com.fiap.cartaocredito.cartaocredito.entrypoints.aluno;

public class AlunoDto {
    private Long rm;
    private String nome;
    private String codigoTurma;

    public AlunoDto(Long rm, String nome, String codigoTurma) {
        this.rm = rm;
        this.nome = nome;
        this.codigoTurma = codigoTurma;
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

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }
}
