package br.com.fiap.cartaocredito.cartaocredito.entrypoints.cartaoCredito;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.service.CartaoCreditoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cartao-credito")
public class CartaoCreditoController {

    private final CartaoCreditoService cartaoCreditoService;

    public CartaoCreditoController(CartaoCreditoService cartaoCreditoService) {
        this.cartaoCreditoService = cartaoCreditoService;
    }

    @GetMapping("/titular/{idTitular}")
    public List<CartaoCreditoDto> buscaCartaoPorIdTitular(@PathVariable Long idTitular){
        List<CartaoCredito> cartoesCredito = cartaoCreditoService.buscaCartaoPorIdTitular(idTitular);

        List<CartaoCreditoDto> cartoesDto = cartoesCredito.stream()
                .map(it -> new CartaoCreditoDto(
                        it.getTitular().getRm(),
                        it.getNumero(),
                        it.getCvc(),
                        it.getVencimento())
                ).collect(Collectors.toList());

        return cartoesDto;
    }
}