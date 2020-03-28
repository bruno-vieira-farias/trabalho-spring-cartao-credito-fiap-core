package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.StatusTransacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Transacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final CartaoCreditoService cartaoCreditoService;

    public TransacaoService(TransacaoRepository transacaoRepository, CartaoCreditoService cartaoCreditoService) {
        this.transacaoRepository = transacaoRepository;
        this.cartaoCreditoService = cartaoCreditoService;
    }

    @Transactional
    public void registraTransacao(TransacaoDto transacaoDto) {
        CartaoCredito cartaoCredito = cartaoCreditoService.buscaCartaoPorId(transacaoDto.getNumeroCartao());

        Transacao transacao = new Transacao(
                transacaoDto.getId(),
                transacaoDto.getDataHoraCriacao(),
                transacaoDto.getValor(),
                StatusTransacao.valueOf(transacaoDto.getStatus().name()),
                transacaoDto.getCodigoAutorizacao(),
                cartaoCredito);

        transacaoRepository.save(transacao);
    }

    @Transactional
    public void registraTransacoes(List<TransacaoDto> transacoesDto) {
        List<Long> numerosCartao = transacoesDto.stream()
                .map(TransacaoDto::getNumeroCartao)
                .collect(Collectors.toList());

        Map<Long, CartaoCredito> cartaoPorNumeroCartao =
                cartaoCreditoService.buscaCartoesCreditoPorNumero(numerosCartao).stream()
                        .collect(Collectors.toMap(CartaoCredito::getNumero, cartao -> cartao));

        List<Transacao> transacoes = transacoesDto.stream().map(it ->
                new Transacao(it.getId(),
                        it.getDataHoraCriacao(),
                        it.getValor(),
                        StatusTransacao.valueOf(it.getStatus().name()),
                        it.getCodigoAutorizacao(),
                        cartaoPorNumeroCartao.get(it.getNumeroCartao())
                )).collect(Collectors.toList());

        transacaoRepository.saveAll(transacoes);
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
