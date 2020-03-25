package br.com.fiap.cartaocredito.cartaocredito.entrypoints.extrato;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Transacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.service.TransacaoService;
import br.com.fiap.cartaocredito.cartaocredito.entrypoints.transacao.StatusTransacaoDto;
import br.com.fiap.cartaocredito.cartaocredito.entrypoints.transacao.TransacaoDto;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/extrato")
public class ExtratoController {

    private TransacaoService transacaoService;

    public ExtratoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public ExtratoDto buscaPorCartao(@RequestParam("numeroCompletoCartao") String numeroCompletoCartao){
        List<TransacaoDto> transacoes = transacaoService.buscaTransacoesPorCartao(numeroCompletoCartao)
         .stream().map(transacao -> new TransacaoDto(
                transacao.getId(),
                transacao.getDataHoraCriacao(),
                transacao.getValor(),
                StatusTransacaoDto.valueOf(transacao.getStatus().name()),
                transacao.getCodigoAutorizacao(),
                transacao.getAluno().getRm()
        )).collect(Collectors.toList());

        return new ExtratoDto(ZonedDateTime.now(), transacoes);
    }
}
