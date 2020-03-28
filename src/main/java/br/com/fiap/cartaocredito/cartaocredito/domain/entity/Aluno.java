package br.com.fiap.cartaocredito.cartaocredito.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Aluno {
    @Id
    private Long rm;
    private String nome;
    private String codigoTurma;

    public Aluno() {
    }

    public Aluno(Long rm, String nome, String codigoTurma) {
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