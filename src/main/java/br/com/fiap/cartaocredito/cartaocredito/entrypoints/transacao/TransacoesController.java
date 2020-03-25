package br.com.fiap.cartaocredito.cartaocredito.entrypoints.transacao;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Transacao;
import br.com.fiap.cartaocredito.cartaocredito.domain.service.TransacaoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacoesController {

    private final TransacaoService transacaoService;

    public TransacoesController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping()
    public void registraTransacao(@RequestBody TransacaoDto transacaoDto) {
        transacaoService.registraTransacao(
                transacaoDto.getId(),
                transacaoDto.getDataHoraCriacao(),
                transacaoDto.getValor(),
                transacaoDto.getStatus().name(),
                transacaoDto.getCodigoAutorizacao(),
                transacaoDto.getRmALuno()
        );
    }

    @GetMapping("/{id}")
    public TransacaoDto buscaPorId(@PathVariable Integer id) {
        Transacao transacao = transacaoService.buscaTransacaoPorId(id);
        return new TransacaoDto(
                transacao.getId(),
                transacao.getDataHoraCriacao(),
                transacao.getValor(),
                StatusTransacaoDto.valueOf(transacao.getStatus().name()),
                transacao.getCodigoAutorizacao(),
                transacao.getAluno().getRm()
        );
    }
}