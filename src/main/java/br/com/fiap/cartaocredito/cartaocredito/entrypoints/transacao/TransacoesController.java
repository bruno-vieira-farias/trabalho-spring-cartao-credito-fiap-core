package br.com.fiap.cartaocredito.cartaocredito.entrypoints.transacao;


import br.com.fiap.cartaocredito.cartaocredito.domain.service.TransacaoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {

    private final TransacaoService transacaoService;

    public TransacoesController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping()
    public void registraTransacao(@RequestBody TransacaoDto transacaoDto) {
        transacaoService.registraTransacao(
                transacaoDto.getDataHoraCriacao(),
                transacaoDto.getValor(),
                transacaoDto.getStatus().name(),
                transacaoDto.getNumeroCartao(),
                transacaoDto.getDigitoCartao(),
                transacaoDto.getCodigoAutorizacao()
        );

    }

}