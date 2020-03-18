package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import br.com.fiap.cartaocredito.cartaocredito.domain.arquivoImportacao.ArquivoImportacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Aluno;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.CartaoCreditoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImportacaoArquivoAlunoCartaoCreditoService {
    private final CartaoCreditoRepository cartaoCreditoRepository;
    private final AlunoService alunoService;

    public ImportacaoArquivoAlunoCartaoCreditoService(CartaoCreditoRepository cartaoCreditoRepository, AlunoService alunoService) {
        this.cartaoCreditoRepository = cartaoCreditoRepository;
        this.alunoService = alunoService;
    }

    @Transactional
    public void importaArquivoAlunoCartaoCredito(String conteudoArquivo) {
        ArquivoImportacao arquivoImportacao = new ArquivoImportacao(conteudoArquivo);
        List<Aluno> alunos = obtemAlunos(arquivoImportacao);

        cadastraAlunos(alunos);
        cadastraCartoes(arquivoImportacao);
    }

    private List<Aluno> obtemAlunos(ArquivoImportacao arquivoImportacao) {
        return arquivoImportacao.getLinhas().stream()
                .map(it -> new Aluno(
                        it.getRm(),
                        it.getNome()
                        )
                ).collect(Collectors.toList());
    }

    private void cadastraAlunos(List<Aluno> alunos) {
        alunoService.cadastraAlunos(alunos);
    }

    private void cadastraCartoes(ArquivoImportacao arquivo) {
        Map<Long, Aluno> alunosPorRm = obtemMapAlunosPorRm(arquivo);

        List<CartaoCredito> cartoes = arquivo.getLinhas().stream()
                .map(it -> new CartaoCredito(
                        it.getNumeroCartao(),
                        it.getDigitoCartao(),
                        alunosPorRm.get(it.getRm())
                )).collect(Collectors.toList());

        cartaoCreditoRepository.saveAll(cartoes);
    }

    private Map<Long, Aluno> obtemMapAlunosPorRm(ArquivoImportacao arquivoImportacao) {
        return arquivoImportacao.getLinhas().stream()
                .map(it -> new Aluno(it.getRm(), it.getNome())
                ).collect(Collectors.toMap(Aluno::getRm, it -> it));
    }
}