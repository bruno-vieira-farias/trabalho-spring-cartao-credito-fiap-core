package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCreditoPk;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.StatusTransacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Transacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.CartaoCreditoRepository;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final CartaoCreditoRepository cartaoCreditoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, CartaoCreditoRepository cartaoCreditoRepository) {
        this.transacaoRepository = transacaoRepository;
        this.cartaoCreditoRepository = cartaoCreditoRepository;
    }

    @Transactional
    public void registraTransacao(Integer id, ZonedDateTime dataHoraCriacao, BigDecimal valor, String name, Long numeroCartao, Long digitoCartao, String codigoAutorizacao) {
        certificaQueTransacaoPodeSerCriada();

        Optional<CartaoCredito> cartaoCredito = cartaoCreditoRepository.findById(new CartaoCreditoPk(numeroCartao,digitoCartao));

        if (!cartaoCredito.isPresent()){
            throw new IllegalArgumentException("Não existe um cartão cadastrado para esta transacao");
        }

        Transacao transacao = new Transacao(id, dataHoraCriacao, valor, StatusTransacao.valueOf(name), codigoAutorizacao, cartaoCredito.get());
        transacaoRepository.save(transacao);
    }

    @Transactional
    public Transacao buscaTransacaoPorId(Integer id){
        //Todo - Tratar caso nao exista
        return transacaoRepository.findById(id).get();
    }

    private void certificaQueTransacaoPodeSerCriada(){/*Todo Implementar.*/}
}
