package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Aluno;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public Aluno buscaAlunoPorRm(Long rm) {
        Aluno aluno = alunoRepository.findByRm(rm);
        if (aluno == null) {
            throw new IllegalArgumentException("O Aluno não foi encontrado");
        }
        return aluno;
    }

    @Transactional
    public void cadastraAlunos(List<Aluno> alunos) {
        List<Long> rms = alunos.stream()
                .map(Aluno::getRm)
                .collect(Collectors.toList());

//        if (alunoRepository.existsByRm(rms)) {
//            throw new IllegalArgumentException("Já existe um aluno cadastrado com o rm informado.");
//        }

        alunoRepository.saveAll(alunos);
    }

    @Transactional
    public Aluno cadastraAluno(Long rm, String nome) {
        certificaQueAlunoPodeSerCriado(rm);

        Aluno aluno = new Aluno(rm, nome);
        return alunoRepository.save(aluno);
    }

    private void certificaQueAlunoPodeSerCriado(Long rm) {
        if (alunoRepository.existsByRm(rm)) {
            throw new IllegalArgumentException("Já existe um aluno cadastrado com o rm informado.");
        }
    }

}