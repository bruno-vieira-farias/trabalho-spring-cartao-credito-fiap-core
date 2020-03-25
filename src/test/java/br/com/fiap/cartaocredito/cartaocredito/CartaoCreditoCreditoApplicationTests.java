package br.com.fiap.cartaocredito.cartaocredito;

import br.com.fiap.cartaocredito.cartaocredito.domain.repository.AlunoRepository;
import br.com.fiap.cartaocredito.cartaocredito.entrypoints.aluno.AlunoController;
import br.com.fiap.cartaocredito.cartaocredito.entrypoints.aluno.AlunoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = CartaoCreditoApplication.class)
//@WebMvcTest(controllers = AlunoController.class)
@SpringBootTest
@AutoConfigureMockMvc
class CartaoCreditoCreditoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private AlunoRepository alunoRepository;

	@Test
	void contextLoads() throws  Exception{


		AlunoDto aluno = new AlunoDto(99999L, "Teste Post");

		mockMvc.perform(post("/aluno")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(aluno)))
				.andExpect(status().isCreated());

			mockMvc.perform(get("/aluno/{rm}", 99999))
				.andDo(print()).andExpect(status().isOk());
	}
}
