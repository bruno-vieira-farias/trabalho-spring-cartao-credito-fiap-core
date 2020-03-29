package br.com.fiap.cartaocredito.cartaocredito;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Aluno;
import br.com.fiap.cartaocredito.cartaocredito.domain.entity.CartaoCredito;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.AlunoRepository;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.CartaoCreditoRepository;
import br.com.fiap.cartaocredito.cartaocredito.domain.service.TransacaoDto;
import br.com.fiap.cartaocredito.cartaocredito.entrypoints.aluno.AlunoDto;
import br.com.fiap.cartaocredito.cartaocredito.entrypoints.transacao.StatusTransacaoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CartaoCreditoCreditoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    CartaoCreditoRepository cartaoCreditoRepository;

    @Test
    void contextLoads() throws Exception {

        //AlunoController
        long rm = -99999;
        String codigoTurma = "35SCJ";
        Aluno aluno = alunoRepository.findByRm(rm);

        if (aluno != null) {
            alunoRepository.deleteById(rm);
        }

        AlunoDto alunoDto = new AlunoDto(rm, "Teste Post", codigoTurma);

        //Post novo aluno
        mockMvc.perform(post("/aluno")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(alunoDto)))
                .andExpect(status().isCreated());

        //Get aluno por Rm
        mockMvc.perform(get("/aluno/{rm}", rm))
                .andDo(print()).andExpect(status().isOk());

        //Get alunos por Turma
        mockMvc.perform(get("/aluno/codigo-turma/{codigoTurma}", codigoTurma))
                .andDo(print()).andExpect(status().isOk());

        //Put Aluno
        alunoDto.setNome("Teste Put");
        mockMvc.perform(put("/aluno")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(alunoDto)))
                .andExpect(status().isOk());

        //Delete Aluno
        mockMvc.perform(delete("/aluno/{rm}", rm)).andExpect(status().isOk());

        //TransacoesController
        Integer id = -99999;
        rm = -99998;
        Long numeroCartao = 123412341234L;
        Integer cvc = 123;
        LocalDate vencimento = LocalDate.now().plusYears(2);
        LocalDateTime data = LocalDateTime.now();
        TransacaoDto transacaoDto = new TransacaoDto(id, data, new BigDecimal("1500.99"), StatusTransacaoDto.AUTORIZADA, "Aut -99999", numeroCartao);
        aluno = alunoRepository.findByRm(rm);

        if (aluno == null) {
            aluno = new Aluno(rm, "Teste Post", codigoTurma);
            alunoRepository.save(aluno);
        }

        //Adiciona cartão
        CartaoCredito cartao = new CartaoCredito(aluno, numeroCartao, cvc, vencimento);
        cartaoCreditoRepository.save(cartao);

        //Get cartão por titular
        mockMvc.perform(get("/cartao-credito/titular/{idTitular}", rm))
                .andDo(print()).andExpect(status().isOk());


        //Post Registra transação
        mockMvc.perform(post("/transacao")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(transacaoDto)))
                .andExpect(status().isOk());

        //Get Transação por ID
        mockMvc.perform(get("/transacao/{id}", id))
                .andDo(print()).andExpect(status().isOk());


        //Importa Arquivo
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("lista_alunos.txt");

        MockMultipartFile txtFile = new MockMultipartFile("lista_alunos.txt", "", "text/html", inputStream);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/importacao")
                .file("file", txtFile.getBytes())
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }
}
