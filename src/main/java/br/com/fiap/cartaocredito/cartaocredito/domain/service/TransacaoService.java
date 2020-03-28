package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.StatusTransacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Transacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.TransacaoRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.List;

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
        CartaoCredito cartaoCredito = cartaoCreditoService.buscaCartaoPorNumero(numeroCartao);

        Transacao transacao = new Transacao(id, dataHoraCriacao, valor, StatusTransacao.valueOf(name), codigoAutorizacao, cartaoCredito);
        transacaoRepository.save(transacao);
    }

    @Transactional
    public Transacao buscaTransacaoPorId(Integer id) throws NotFoundException {

        Optional<Transacao> transacao = transacaoRepository.findById(id);

        if(!transacao.isPresent())
            throw new NotFoundException("Transação não encontrada");

        return transacao.get();
    }

    @Transactional
    public List<Transacao> buscaTransacoesPorCartao(Long numeroCartao){

        ValidarCartao(numeroCartao);

        List<Transacao> transacoes = transacaoRepository.findByCartaoCreditoNumero(numeroCartao);

        if(transacoes.size() == 0)
            throw new NoResultException("Nehuma Transação encontrada para este cartão");

        return transacoes;
    }

    private void ValidarCartao(Long numeroCartao) {
        cartaoCreditoService.buscaCartaoPorNumero(numeroCartao);
    }

    private void certificaQueTransacaoPodeSerCriada() {/*Todo Implementar.*/}
}
