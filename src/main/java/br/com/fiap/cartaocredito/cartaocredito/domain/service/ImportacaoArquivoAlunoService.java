package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import br.com.fiap.cartaocredito.cartaocredito.domain.arquivoImportacao.ArquivoImportacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Aluno;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportacaoArquivoAlunoService {
    private final AlunoService alunoService;
    private final CartaoCreditoService cartaoCreditoService;

    public ImportacaoArquivoAlunoService(AlunoService alunoService, CartaoCreditoService cartaoCreditoService) {
        this.alunoService = alunoService;
        this.cartaoCreditoService = cartaoCreditoService;
    }

    @Transactional
    public void importaArquivoAlunoCartaoCredito(String conteudoArquivo) {
        ArquivoImportacao arquivoImportacao = new ArquivoImportacao(conteudoArquivo);
        List<Aluno> alunos = obtemAlunos(arquivoImportacao);

        alunoService.cadastraAlunos(alunos);
        cartaoCreditoService.geraNovoCartaoCredito2(alunos);
    }

    private List<Aluno> obtemAlunos(ArquivoImportacao arquivoImportacao) {
        return arquivoImportacao.getLinhas().stream()
                .map(linha -> new Aluno(
                                linha.getRm(),
                                linha.getNome(),
                                linha.getCodigoTurma())
                ).collect(Collectors.toList());
    }
}