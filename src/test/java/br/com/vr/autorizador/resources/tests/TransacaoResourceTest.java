/**
 * 
 */
package br.com.vr.autorizador.resources.tests;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vr.autorizador.business.TransacaoBusiness;
import br.com.vr.autorizador.entities.Cartao;
import br.com.vr.autorizador.entities.Transacao;
import br.com.vr.autorizador.enums.BodyType;
import br.com.vr.autorizador.resources.TransacaoResource;
import lombok.Getter;

/**
 * 
 */
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransacaoResourceTest {

	/**
	 * 
	 */
	@Getter
	private MockMvc mockMvc;
	
	/**
	 * 
	 */
	@Getter
	@Mock
	private TransacaoBusiness transacaoBusiness;
	
	/**
	 * 
	 */
	@Getter
	@InjectMocks
    private TransacaoResource transacaoResource;
	
	/**
	 * 
	 */
	@BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(getTransacaoResource())
                .build();
    }
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("Primeiro Teste: Processando a transacao")
	@Order(1)
	public void testProcessInsert() throws Exception {
		 final Cartao cartao = Cartao.builder()
				 .numeroCartao("1234567812345678")
	             .senha(1234)
	             .saldo(BigDecimal.valueOf(50))
	             .build();
	        
		 final MvcResult mvcResult = getMockMvc().perform(MockMvcRequestBuilders.post("/transacoes")
				 .contentType(MediaType.APPLICATION_JSON)
	                        .content(new ObjectMapper().writeValueAsString(cartao)))
	                .andReturn();

	     Assertions.assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("Segundo Teste: Validando Cartão inexistente")
	@Order(2)
	public void testProcessInsertCartaoInexiste() throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		 final Cartao cartao = Cartao.builder()
				 .numeroCartao("0000111122223333")
	             .senha(1234)
	             .saldo(BigDecimal.valueOf(50))
	             .build();
		 
		 final Transacao transacao = new Transacao(HttpStatus.UNPROCESSABLE_ENTITY.value(), BodyType.CARTAO_INEXISTENTE);
		 
		 when(getTransacaoBusiness().execute(cartao)).thenReturn(transacao);
	        
		 final MvcResult mvcResult = getMockMvc().perform(MockMvcRequestBuilders.post("/transacoes")
				 .contentType(MediaType.APPLICATION_JSON)
	                        .content(new ObjectMapper().writeValueAsString(cartao)))
	                .andReturn();
		 
		 final Transacao result = mapper.readValue(mvcResult.getResponse().getContentAsString(), Transacao.class);

	     Assertions.assertEquals(BodyType.CARTAO_INEXISTENTE, result.getBody());
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("Terceiro Teste: Validando senha incorreta")
	@Order(3)
	public void testProcessInsertSenhaIncorreta() throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		
		final Cartao cartao = Cartao.builder()
				 .numeroCartao("1234567812345678")
	             .senha(123456)
	             .saldo(BigDecimal.valueOf(50))
	             .build();
		 
		 final Transacao transacao = new Transacao(HttpStatus.UNPROCESSABLE_ENTITY.value(), BodyType.SENHA_INVALIDA);
		 
		 when(getTransacaoBusiness().execute(cartao)).thenReturn(transacao);
	        
		final MvcResult mvcResult = getMockMvc().perform(MockMvcRequestBuilders.post("/transacoes")
				 .contentType(MediaType.APPLICATION_JSON)
	                        .content(new ObjectMapper().writeValueAsString(cartao)))
	                .andReturn();
		 
		final Transacao result = mapper.readValue(mvcResult.getResponse().getContentAsString(), Transacao.class);

	    Assertions.assertEquals(BodyType.SENHA_INVALIDA, result.getBody());
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("Quarto Teste: Validando saldo insuficiente")
	@Order(4)
	public void testProcessInsertSaldoInsuficiente() throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		
		final Cartao cartao = Cartao.builder()
				 .numeroCartao("1234567812345678")
	             .senha(123456)
	             .saldo(BigDecimal.valueOf(50))
	             .build();
		 
		 final Transacao transacao = new Transacao(HttpStatus.UNPROCESSABLE_ENTITY.value(), BodyType.SALDO_INSUFICIENTE);
		 
		 when(getTransacaoBusiness().execute(cartao)).thenReturn(transacao);
	        
		final MvcResult mvcResult = getMockMvc().perform(MockMvcRequestBuilders.post("/transacoes")
				 .contentType(MediaType.APPLICATION_JSON)
	                        .content(new ObjectMapper().writeValueAsString(cartao)))
	                .andReturn();
		 
		final Transacao result = mapper.readValue(mvcResult.getResponse().getContentAsString(), Transacao.class);

	    Assertions.assertEquals(BodyType.SALDO_INSUFICIENTE, result.getBody());
	}
}
