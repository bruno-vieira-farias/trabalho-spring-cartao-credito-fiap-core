package br.com.fiap.cartaocredito.cartaocredito.entrypoints.Transacao;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {

    @PostMapping()
    public void registraTransacao(@RequestBody TransacaoDto transacoes){

    }

}