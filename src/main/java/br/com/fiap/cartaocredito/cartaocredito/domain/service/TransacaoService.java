package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.StatusTransacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Transacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.TransacaoRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
        CartaoCredito cartaoCredito = cartaoCreditoService.buscaCartaoPorNumero(transacaoDto.getNumeroCartao());

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
        Set<Long> numerosCartao = transacoesDto.stream()
                .map(TransacaoDto::getNumeroCartao)
                .collect(Collectors.toSet());

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
    public Transacao buscaTransacaoPorId(Integer id) throws NotFoundException {
        Optional<Transacao> transacao = transacaoRepository.findById(id);

        if (!transacao.isPresent())
            throw new NotFoundException("Transação não encontrada");

        return transacao.get();
    }

    @Transactional
    public List<Transacao> buscaTransacoesPorCartao(Long numeroCartao) {
        certificaQueCartaoExiste(numeroCartao);
        List<Transacao> transacoes = transacaoRepository.findByCartaoCreditoNumero(numeroCartao);

        if (transacoes.size() == 0)
            throw new NoResultException("Nenhuma Transação foi encontrada para este cartão");

        return transacoes;
    }

    private void certificaQueCartaoExiste(Long numeroCartao) {
        cartaoCreditoService.buscaCartaoPorNumero(numeroCartao);
    }
}
