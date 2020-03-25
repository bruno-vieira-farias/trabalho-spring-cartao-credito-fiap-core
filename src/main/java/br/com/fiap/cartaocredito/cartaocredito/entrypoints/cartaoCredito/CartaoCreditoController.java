package br.com.fiap.cartaocredito.cartaocredito.entrypoints.cartaoCredito;


import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.service.CartaoCreditoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cartaoCredito")
public class CartaoCreditoController {
    private final CartaoCreditoService cartaoCreditoService;

    public CartaoCreditoController(CartaoCreditoService cartaoCreditoService) {
        this.cartaoCreditoService = cartaoCreditoService;
    }

    @GetMapping("/aluno/{rm}")
    private List<CartaoCreditoDto> buscaCartaoCreditoPorRmAluno(@PathVariable Long rm) {
        try {
            List<CartaoCredito> listaCartaoCredito = cartaoCreditoService.buscaCartaoCreditoPorRmAluno(rm);

            List<CartaoCreditoDto> listaCartaoCreditoDto = listaCartaoCredito.stream()
                    .map(it -> new CartaoCreditoDto(rm, it.getId().getNumero(), it.getId().getDigitoVerificador()))
                    .collect(Collectors.toList());

            return listaCartaoCreditoDto;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/aluno")
    private List<CartaoCreditoDto> buscaTodosCartoesCredito() {
        try {
            List<CartaoCredito> listaCartaoCredito = cartaoCreditoService.buscaTodosCartoesCredito();

            List<CartaoCreditoDto> listaCartaoCreditoDto = listaCartaoCredito.stream()
                    .map(it -> new CartaoCreditoDto(it.getTitular().getRm(), it.getId().getNumero(), it.getId().getDigitoVerificador()))
                    .collect(Collectors.toList());

            return listaCartaoCreditoDto;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void cadastraCartaoCredito(@RequestBody CartaoCreditoDto cartaoCreditoDto) {
        try {
            cartaoCreditoService.cadastraCartaoCredito(cartaoCreditoDto.getRmAluno(), cartaoCreditoDto.getNumero(), cartaoCreditoDto.getDigitoVerificador());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @DeleteMapping("{numero}/{digito}")
    private void deletaCartaoCredito(@PathVariable Long numero, Long digito) {
        try {
            cartaoCreditoService.deletaCartaoCredito(numero, digito);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

}