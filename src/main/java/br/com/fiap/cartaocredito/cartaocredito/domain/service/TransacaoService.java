package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.StatusTransacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Transacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final CartaoCreditoService cartaoCreditoService;

    public TransacaoService(TransacaoRepository transacaoRepository, CartaoCreditoService cartaoCreditoService) {
        this.transacaoRepository = transacaoRepository;
        this.cartaoCreditoService = cartaoCreditoService;
    }

    @Transactional
    public void registraTransacao(Integer id, ZonedDateTime dataHoraCriacao, BigDecimal valor, String name, String codigoAutorizacao, Long numeroCartao) {
        CartaoCredito cartaoCredito = cartaoCreditoService.buscaCartaoPorId(numeroCartao);

        Transacao transacao = new Transacao(id, dataHoraCriacao, valor, StatusTransacao.valueOf(name), codigoAutorizacao, cartaoCredito);
        transacaoRepository.save(transacao);
    }

    @Transactional
    public Transacao buscaTransacaoPorId(Integer id) {
        Optional<Transacao> transacao = transacaoRepository.findById(id);

        if (!transacao.isPresent()) {
            throw new IllegalArgumentException("A Transacao não foi encontrada.");
        }
        return transacao.get();
    }
}
