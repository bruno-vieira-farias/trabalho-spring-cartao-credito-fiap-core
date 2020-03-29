package br.com.fiap.cartaocredito.cartaocredito.entrypoints.extrato;


import br.com.fiap.cartaocredito.cartaocredito.domain.service.TransacaoDto;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public class ExtratoDto {
    private final ZonedDateTime dataExtracao;
    private final List<TransacaoDto> transacoes;

    public ExtratoDto(ZonedDateTime dataExtracao, List<TransacaoDto> transacoes) {
        this.dataExtracao = dataExtracao;
        this.transacoes = transacoes;
    }

    public ZonedDateTime getDataExtracao() {
        return dataExtracao;
    }

    public List<TransacaoDto> getTransacoes() {
        return transacoes;
    }

    @Override
    public String toString() {

        Optional<TransacaoDto> transacoes = getTransacoes().stream().findFirst();

        if(!transacoes.isPresent())
            return "";

        Long numeroCartao = transacoes.get().getNumeroCartao();

        StringBuilder append =
                new StringBuilder(" Cartão: ").append(numeroCartao.toString()).append("\n")
                        .append(" Data da Extração: ").append(getDataExtracao())
                        .append("\n")
                        .append("\n")
                        .append(" Transações: ")
                        .append("\n");
        for (TransacaoDto transacao : getTransacoes()) {
            append.append(transacao.toString()).append("\n");
        }

        return append.append("\n").toString();
    }
}
