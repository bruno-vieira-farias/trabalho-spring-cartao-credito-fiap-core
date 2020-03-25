package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Aluno;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.StatusTransacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Transacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final AlunoService alunoService;

    public TransacaoService(TransacaoRepository transacaoRepository, AlunoService alunoService) {
        this.transacaoRepository = transacaoRepository;
        this.alunoService = alunoService;
    }

    @Transactional
    public void registraTransacao(Integer id, ZonedDateTime dataHoraCriacao, BigDecimal valor, String name, String codigoAutorizacao, Long rmAluno) {
//        certificaQueTransacaoPodeSerCriada();
        Aluno aluno = alunoService.buscaAlunoPorRm(rmAluno);

        Transacao transacao = new Transacao(id, dataHoraCriacao, valor, StatusTransacao.valueOf(name), codigoAutorizacao, aluno);
        transacaoRepository.save(transacao);
    }

    @Transactional
    public Transacao buscaTransacaoPorId(Integer id) {
        //Todo - Tratar caso nao exista
        return transacaoRepository.findById(id).get();
    }

    private void certificaQueTransacaoPodeSerCriada() {/*Todo Implementar.*/}
}
