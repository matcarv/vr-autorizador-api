/**
 * 
 */
package br.com.vr.autorizador.resources.tests;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Set;

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

import br.com.vr.autorizador.business.CartaoBusiness;
import br.com.vr.autorizador.entities.Cartao;
import br.com.vr.autorizador.resources.CartaoResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.Getter;

/**
 * 
 */
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartaoResourceTest {

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
	private CartaoBusiness cartaoBusiness;
	
	/**
	 * 
	 */
	@Getter
	@InjectMocks
    private CartaoResource cartaoResource;
	
	/**
	 * 
	 */
	@BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(getCartaoResource())
                .build();
    }
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("Primeiro Teste: Processando a inclusáo do Cartão")
	@Order(1)
	public void testProcessInsert() throws Exception {
		 final Cartao entity = Cartao.builder()
				 .numeroCartao("1234567812345678")
	             .senha(1234)
	             .saldo(BigDecimal.valueOf(500))
	             .build();
	        
		 final MvcResult mvcResult = getMockMvc().perform(MockMvcRequestBuilders.post("/cartoes")
				 .contentType(MediaType.APPLICATION_JSON)
	                        .content(new ObjectMapper().writeValueAsString(entity)))
	                .andReturn();

	        Assertions.assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("Segundo Teste: Buscando o Saldo do Cartão")
	@Order(2)
	public void testFindSaldoByNumeroCartao() throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		final BigDecimal saldo = BigDecimal.valueOf(500);
		 
		when(getCartaoBusiness().findSaldoByNumeroCartao("1234567812345678")).thenReturn(saldo);
		 
		final MvcResult mvcResult = getMockMvc().perform(
				 MockMvcRequestBuilders.get("/cartoes/{numeroCartao}", "1234567812345678")
				 .accept(MediaType.APPLICATION_JSON))
				 .andReturn();

		final BigDecimal result = mapper.readValue(mvcResult.getResponse().getContentAsString(), BigDecimal.class);
		 
	    Assertions.assertEquals(saldo, result);
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("Terceiro Teste: Validando a obrigatoriedade do três campos requeridos.")
	@Order(3)
	public void testProcessInsertNumeroCartaoRequerido() throws Exception {
		final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		final Cartao entity = Cartao.builder()
	             .build();
		
		final Set<ConstraintViolation<Cartao>> violations = validator.validate(entity);
		violations.stream().forEach(e -> {
			System.out.println("     -----> " + e.getMessageTemplate());
		});

        Assertions.assertEquals(3, violations.size());
	}
}
