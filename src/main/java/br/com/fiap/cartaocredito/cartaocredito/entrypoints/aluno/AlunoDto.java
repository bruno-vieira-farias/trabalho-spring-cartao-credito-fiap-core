package br.com.fiap.cartaocredito.cartaocredito.entrypoints.aluno;

public class AlunoDto {
    private Long rm;
    private String nome;
    private String numeroCompletoCartaoCredito;

    public AlunoDto(Long rm, String nome, String numeroCompletoCartaoCredito) {
        this.rm = rm;
        this.nome = nome;
        this.numeroCompletoCartaoCredito = numeroCompletoCartaoCredito;
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

    public String getNumeroCompletoCartaoCredito() {
        return numeroCompletoCartaoCredito;
    }

    public void setNumeroCompletoCartaoCredito(String numeroCompletoCartaoCredito) {
        this.numeroCompletoCartaoCredito = numeroCompletoCartaoCredito;
    }
}
