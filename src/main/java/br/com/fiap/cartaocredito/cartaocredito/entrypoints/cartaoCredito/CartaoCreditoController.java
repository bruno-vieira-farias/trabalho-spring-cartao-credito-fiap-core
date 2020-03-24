package br.com.fiap.cartaocredito.cartaocredito.entrypoints.cartaoCredito;


import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.service.CartaoCreditoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/cartaoCredito")
public class CartaoCreditoController {
    private final CartaoCreditoService cartaoCreditoService;

    public CartaoCreditoController(CartaoCreditoService cartaoCreditoService) {
        this.cartaoCreditoService = cartaoCreditoService;
    }

    @GetMapping("/aluno/{rm}")
    private CartaoCreditoDto buscaCartaoCreditoPorRmAluno(@PathVariable Long rm) {
        try {
            CartaoCredito cartaoCredito = cartaoCreditoService.buscaCartaoCreditoPorRmAluno(rm);
            return new CartaoCreditoDto(rm, cartaoCredito.getId().getNumero(), cartaoCredito.getId().getDigitoVerificador());
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