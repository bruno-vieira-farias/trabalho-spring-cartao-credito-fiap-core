package br.com.fiap.cartaocredito.cartaocredito.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Aluno {
    @Id
    private Long rm;
    private String nome;
    private Long numeroCartao;
    private Long digitoVerificadorCartao;

    public Aluno() {
    }

    public Aluno(Long rm, String nome, Long numeroCartao, Long digitoVerificadorCartao) {
        this.rm = rm;
        this.nome = nome;
        this.numeroCartao = numeroCartao;
        this.digitoVerificadorCartao = digitoVerificadorCartao;
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

    public void setNumeroCartao(Long numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Long getDigitoVerificadorCartao() {
        return digitoVerificadorCartao;
    }

    public void setRm(Long rm) {
        this.rm = rm;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDigitoVerificadorCartao(Long digitoVerificadorCartao) {
        this.digitoVerificadorCartao = digitoVerificadorCartao;
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
