package br.com.fiap.cartaocredito.cartaocredito.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public Aluno buscaAlunoPorRm(Long rm){
        Aluno aluno = alunoRepository.findByRm(rm);
        if (aluno == null){
             throw new IllegalArgumentException("O Aluno não foi encontrado");
        }
        return aluno;
    }

    public void cadastraAluno(Long rm, String nome, Long numeroCartao, Long digitoCartao){
        certificaQueAlunoPodeSerCriado(rm);
        Aluno aluno = new Aluno(rm,nome,numeroCartao,digitoCartao);
        alunoRepository.save(aluno);
    }

    private void certificaQueAlunoPodeSerCriado(Long rm){
        if (alunoRepository.findByRm(rm) != null) {
            throw new IllegalArgumentException("Já existe um aluno cadastrado com o rm informado.");
        }
    }

}
