package br.com.fiap.cartaocredito.cartaocredito.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Aluno {
    @Id
    private Long rm;
    private String nome;
    private String numeroCompletoCartaoCredito;

    public Aluno() {
    }

    public Aluno(Long rm, String nome, String numeroCompletoCartaoCredito) {
        this.rm = rm;
        this.nome = nome;
        this.numeroCompletoCartaoCredito = numeroCompletoCartaoCredito;
    }

    public String getNome() {
        return nome;
    }

    public Long getRm() {
        return rm;
    }

    public void setRm(Long rm) {
        this.rm = rm;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(rm, aluno.rm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rm);
    }
}