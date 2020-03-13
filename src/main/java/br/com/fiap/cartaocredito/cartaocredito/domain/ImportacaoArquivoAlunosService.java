package br.com.fiap.cartaocredito.cartaocredito.domain;

import br.com.fiap.cartaocredito.cartaocredito.domain.Aluno;
import br.com.fiap.cartaocredito.cartaocredito.domain.AlunoRepository;
import br.com.fiap.cartaocredito.cartaocredito.domain.arquivoAluno.ArquivoImportacaoAluno;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportacaoArquivoAlunosService {

    private final AlunoRepository alunoRepository;

    public ImportacaoArquivoAlunosService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public void importaAlunos(String conteudoArquivo) {
        ArquivoImportacaoAluno arquivo = new ArquivoImportacaoAluno(conteudoArquivo);

        List<Aluno> alunos = arquivo.getLinhas().stream()
                .map(it -> new Aluno(
                        it.getRm(),
                        it.getNome(),
                        it.getNumeroCartao(),
                        it.getDigitoCartao()
                )
        ).collect(Collectors.toList());

        alunoRepository.saveAll(alunos);
    }
}