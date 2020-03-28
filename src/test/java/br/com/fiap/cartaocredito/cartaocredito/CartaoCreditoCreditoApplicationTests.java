package br.com.fiap.cartaocredito.cartaocredito;

import br.com.fiap.cartaocredito.cartaocredito.domain.entity.Aluno;
import br.com.fiap.cartaocredito.cartaocredito.domain.repository.AlunoRepository;
import br.com.fiap.cartaocredito.cartaocredito.entrypoints.aluno.AlunoDto;
import br.com.fiap.cartaocredito.cartaocredito.entrypoints.transacao.StatusTransacaoDto;
import br.com.fiap.cartaocredito.cartaocredito.entrypoints.transacao.TransacaoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

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
        ZonedDateTime data = ZonedDateTime.ofInstant(new Date().toInstant(), ZoneOffset.systemDefault());
        TransacaoDto transacaoDto = new TransacaoDto(id, data, new BigDecimal("1500.99"), StatusTransacaoDto.AUTORIZADA, "Aut -99999", rm);
		aluno = alunoRepository.findByRm(rm);

		if (aluno == null) {
            aluno = new Aluno(rm, "Teste Post", codigoTurma);
			alunoRepository.save(aluno);
		}

        //Post Registra transação
        mockMvc.perform(post("/transacao")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(transacaoDto)))
                .andExpect(status().isOk());

		//Get Transação por ID
        mockMvc.perform(get("/transacao/{id}", id))
                .andDo(print()).andExpect(status().isOk());
	}
}
