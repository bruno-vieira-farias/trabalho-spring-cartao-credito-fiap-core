package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Aluno;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.CartaoCreditoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartaoCreditoService {

    private final AlunoService alunoService;
    private final CartaoCreditoRepository cartaoCreditoRepository;
    private final GeradorCartaoCreditoService geradorCartaoCreditoService;

    public CartaoCreditoService(AlunoService alunoService, CartaoCreditoRepository cartaoCreditoRepository, GeradorCartaoCreditoService geradorCartaoCreditoService) {
        this.alunoService = alunoService;
        this.cartaoCreditoRepository = cartaoCreditoRepository;
        this.geradorCartaoCreditoService = geradorCartaoCreditoService;
    }

    @Transactional
    public void geraNovoCartaoCredito(Long rmAluno) {
        Aluno aluno = alunoService.buscaAlunoPorRm(rmAluno);
        CartaoCreditoDto novoCartaoDto = geradorCartaoCreditoService.obtemCartaoNovo();

        CartaoCredito cartaoCredito = new CartaoCredito(
                                                aluno,
                                                novoCartaoDto.getNumero(),
                                                novoCartaoDto.getCvc(),
                                                novoCartaoDto.getVencimento()
        );

        cartaoCreditoRepository.save(cartaoCredito);
    }

    @Transactional
    public void geraNovoCartaoCredito2(List<Aluno> alunos) {

        List<CartaoCredito> cartoes = alunos.stream().map(aluno -> {
                    CartaoCreditoDto novoCartaoDto = geradorCartaoCreditoService.obtemCartaoNovo();
                    return new CartaoCredito(
                            aluno,
                            novoCartaoDto.getNumero(),
                            novoCartaoDto.getCvc(),
                            novoCartaoDto.getVencimento());
                }
        ).collect(Collectors.toList());

        cartaoCreditoRepository.saveAll(cartoes);
    }

    @Transactional
    public List<CartaoCredito> buscaCartaoPorIdTitular(Long idTitular){
        return cartaoCreditoRepository.findByTitular_Rm(idTitular);
    }

    @Transactional
    public CartaoCredito buscaCartaoPorNumero(Long numero){
        Optional<CartaoCredito> cartao = cartaoCreditoRepository.findById(numero);

        if (!cartao.isPresent()) {
            throw new IllegalArgumentException("O Cartao não foi encontrado");
        }

        return cartao.get();
    }

}
