package br.com.fiap.cartaocredito.cartaocredito.entrypoints.extrato;

import br.com.fiap.cartaocredito.cartaocredito.entrypoints.transacao.TransacaoDto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class ExtratoDto {
    private ZonedDateTime dataExtracao;
    private List<TransacaoDto> transacoes;

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
}
