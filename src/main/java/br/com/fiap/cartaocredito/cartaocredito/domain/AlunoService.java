package br.com.fiap.cartaocredito.cartaocredito.domain;

import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno buscaAlunoPorRm(Long rm){
        Aluno aluno = alunoRepository.findByRm(rm);
        if (aluno == null){
             throw new IllegalArgumentException("O Aluno não foi encontrado");
        }

        return aluno;
    }
}
