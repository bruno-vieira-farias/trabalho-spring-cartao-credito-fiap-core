package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Aluno;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
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
        Set<Long> rms = alunos.stream()
                .map(Aluno::getRm)
                .collect(Collectors.toSet());

        if (alunoRepository.existsAllByRmIn(rms)) {
            throw new IllegalArgumentException("Já existe um aluno cadastrado com o rm informado.");
        }

        alunoRepository.saveAll(alunos);
    }

    @Transactional
    public void cadastraAluno(Long rm, String nome) {
        certificaQueAlunoPodeSerCriado(rm);

        Aluno aluno = new Aluno(rm, nome);
        alunoRepository.save(aluno);
    }

    @Transactional
    public void atualizaAluno(Long rm, String nome){
        Aluno aluno = buscaAlunoPorRm(rm);
        aluno.setNome(nome);
        alunoRepository.save(aluno);
    }

    @Transactional
    public void deletaALuno(Long rm){
        alunoRepository.deleteById(rm);
    }

    private void certificaQueAlunoPodeSerCriado(Long rm) {
        if (alunoRepository.existsByRm(rm)) {
            throw new IllegalArgumentException("Já existe um aluno cadastrado com o rm informado.");
        }
    }

}