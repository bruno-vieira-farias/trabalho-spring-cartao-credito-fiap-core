package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import br.com.fiap.cartaocredito.cartaocredito.domain.arquivoImportacao.ArquivoImportacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Aluno;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.AlunoRepository;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.CartaoCreditoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImportacaoArquivoAlunoCartaoCreditoService {
    private final AlunoRepository alunoRepository;
    private final CartaoCreditoRepository cartaoCreditoRepository;

    public ImportacaoArquivoAlunoCartaoCreditoService(AlunoRepository alunoRepository, CartaoCreditoRepository cartaoCreditoRepository) {
        this.alunoRepository = alunoRepository;
        this.cartaoCreditoRepository = cartaoCreditoRepository;
    }

    @Transactional
    public void importaArquivoAlunoCartaoCredito(String conteudoArquivo) {
        ArquivoImportacao arquivoImportacao = new ArquivoImportacao(conteudoArquivo);
        Map<Long, Aluno> alunosPorRm = obtemMapAlunosPorRm(arquivoImportacao);

        cadastraAlunos(alunosPorRm.values());
        cadastraCartoes(arquivoImportacao, alunosPorRm);
    }

    private Map<Long, Aluno> obtemMapAlunosPorRm(ArquivoImportacao arquivoImportacao){
        return arquivoImportacao.getLinhas().stream()
                .map(it -> new Aluno(it.getRm(), it.getNome())
                ).collect(Collectors.toMap(Aluno::getRm, it -> it));
    }

    private void cadastraAlunos(Collection<Aluno> alunos) {
        alunoRepository.saveAll(alunos);
    }

    private void cadastraCartoes(ArquivoImportacao arquivo, Map<Long, Aluno> alunosPorRm) {
        List<CartaoCredito> cartoes = arquivo.getLinhas().stream()
                .map(it -> new CartaoCredito(
                        it.getNumeroCartao(),
                        it.getDigitoCartao(),
                        alunosPorRm.get(it.getRm())
                )).collect(Collectors.toList());

        cartaoCreditoRepository.saveAll(cartoes);
    }
}