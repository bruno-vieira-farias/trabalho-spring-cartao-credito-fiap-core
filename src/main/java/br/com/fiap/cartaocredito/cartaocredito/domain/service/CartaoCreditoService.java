package br.com.fiap.cartaocredito.cartaocredito.domain.service;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Aluno;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCreditoPk;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.CartaoCreditoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartaoCreditoService {

    private final CartaoCreditoRepository cartaoCreditoRepository;
    private final AlunoService alunoService;

    public CartaoCreditoService(CartaoCreditoRepository cartaoCreditoRepository, AlunoService alunoService) {
        this.cartaoCreditoRepository = cartaoCreditoRepository;
        this.alunoService = alunoService;
    }

    @Transactional
    public List<CartaoCredito> buscaCartaoCreditoPorRmAluno(Long rmAluno) {
        return cartaoCreditoRepository.findByTitular_Rm(rmAluno);
    }

    @Transactional
    public List<CartaoCredito> buscaTodosCartoesCredito() {
        return cartaoCreditoRepository.findAll();
    }

    @Transactional
    public void cadastraCartaoCredito(Long rmAluno, Long numero, Long digitoVerificador) {
        Aluno aluno = alunoService.buscaAlunoPorRm(rmAluno);
        CartaoCredito cartaoCredito = new CartaoCredito(numero, digitoVerificador, aluno);

        cartaoCreditoRepository.save(cartaoCredito);
    }

    @Transactional
    public void deletaCartaoCredito(Long numero, Long digitoVerificador) {
        CartaoCreditoPk cartaoCreditoPk = new CartaoCreditoPk(numero, digitoVerificador);
        cartaoCreditoRepository.deleteById(cartaoCreditoPk);
    }
}
