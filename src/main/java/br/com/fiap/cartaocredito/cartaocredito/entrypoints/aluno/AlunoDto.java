package br.com.fiap.cartaocredito.cartaocredito.entrypoints.aluno;

public class AlunoDto {
    private Long rm;
    private String nome;

    public AlunoDto(Long rm, String nome) {
        this.rm = rm;
        this.nome = nome;

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
}
