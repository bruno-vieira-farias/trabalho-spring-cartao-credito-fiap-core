package br.com.fiap.cartaocredito.cartaocredito.entrypoints.extrato;

import br.com.fiap.cartaocredito.cartaocredito.domain.service.TransacaoDto;
import br.com.fiap.cartaocredito.cartaocredito.domain.service.TransacaoService;
import br.com.fiap.cartaocredito.cartaocredito.entrypoints.transacao.StatusTransacaoDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.NoResultException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/extrato")
public class ExtratoController {

    private TransacaoService transacaoService;

    public ExtratoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public ExtratoDto buscaPorCartao(@RequestParam("numeroCartao") Long numeroCartao){
        try {
            return ObterExtrato(numeroCartao);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (NoResultException e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(), e);
        }
    }

    @GetMapping("/download")
    public void download(@RequestParam("numeroCartao") Long numeroCartao, HttpServletResponse response){
        try {
            ExtratoDto extratoDto = ObterExtrato(numeroCartao);

            response.setContentType("text/plain");
            response.setHeader("Content-Disposition","attachment;filename=extrato.txt");
            ServletOutputStream out = response.getOutputStream();
            out.println(extratoDto.toString());
            out.flush();
            out.close();
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,e.getMessage(), e);
        } catch (NoResultException e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(), e);
        }
    }

    private ExtratoDto ObterExtrato(@RequestParam("numeroCartao") Long numeroCartao) {
        List<TransacaoDto> transacoes = transacaoService.buscaTransacoesPorCartao(numeroCartao)
         .stream().map(transacao -> new TransacaoDto(
                transacao.getId(),
                transacao.getDataHoraCriacao(),
                transacao.getValor(),
                StatusTransacaoDto.valueOf(transacao.getStatus().name()),
                transacao.getCodigoAutorizacao(),
                transacao.getCartaoCredito().getNumero()
        )).collect(Collectors.toList());

        return new ExtratoDto(ZonedDateTime.now(), transacoes);
    }
}
