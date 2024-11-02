/**
 * 
 */
package br.com.vr.autorizador.business.tests;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;

import br.com.vr.autorizador.business.CartaoBusiness;
import br.com.vr.autorizador.business.TransacaoBusiness;
import br.com.vr.autorizador.entities.Cartao;
import br.com.vr.autorizador.entities.Transacao;
import br.com.vr.autorizador.enums.BodyType;
import lombok.Getter;

/**
 * 
 */
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransacaoBusinessTest {

	/**
	 * 
	 */
	@Getter
	@Autowired
	private TransacaoBusiness transacaoBusiness;
	
	/**
	 * 
	 */
	@Getter
	@Autowired
	private CartaoBusiness cartaoBusiness;
	
	/**
	 * 
	 */
	@BeforeEach
	public void init() {
		 final Cartao entity = Cartao.builder()
	                .numeroCartao("0000111122223333")
	                .senha(1234)
	                .saldo(BigDecimal.valueOf(500))
	                .build();

	     getCartaoBusiness().processInsert(entity);
	}
	
	/**
	 * 
	 */
	@Test
    @DisplayName("Primeiro Teste: Processando a execução OK do cartão")
	@Order(1)
	public void executeTest() {
		 final Cartao cartao = Cartao.builder()
	                .numeroCartao("0000111122223333")
	                .senha(1234)
	                .saldo(BigDecimal.valueOf(30))
	                .build();
		
		final Transacao entity = getTransacaoBusiness().execute(cartao);
		
		Assertions.assertEquals(entity.getBody(), BodyType.OK);
	}
	
	/**
	 * 
	 */
	@Test
    @DisplayName("Segundo Teste: Processando a execução retornando o codigo 422")
	@Order(2)
	public void testExecuteUnprocessableEntity() {
		 final Cartao cartao = Cartao.builder()
	                .numeroCartao("0000111122223333")
	                .senha(1234)
	                .saldo(BigDecimal.valueOf(510))
	                .build();
		
		final Transacao entity = getTransacaoBusiness().execute(cartao);
		
		Assertions.assertEquals(entity.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY.value());
	}
	
	/**
	 * 
	 */
	@Test
    @DisplayName("Terceiro Teste: Processando a execução OK do cartão, validando o saldo atual.")
	@Order(3)
	public void executeValidarSaldoTest() {
		 final Cartao cartao = Cartao.builder()
	                .numeroCartao("0000111122223333")
	                .senha(1234)
	                .saldo(BigDecimal.valueOf(15))
	                .build();
		
		getTransacaoBusiness().execute(cartao);
		
		final Cartao entity = getCartaoBusiness().findByNumeroCartao("0000111122223333");
		
		Assertions.assertEquals(entity.getSaldo(), BigDecimal.valueOf(485));
	}
	
	/**
	 * 
	 */
	@Test
    @DisplayName("Quarto Teste: Processando a execução com Senha Inválida")
	@Order(4)
	public void executeSenhaInvalidaTest() {
		 final Cartao cartao = Cartao.builder()
	                .numeroCartao("0000111122223333")
	                .senha(6964)
	                .saldo(BigDecimal.valueOf(15))
	                .build();
		
		final Transacao entity = getTransacaoBusiness().execute(cartao);
		
		Assertions.assertEquals(entity.getBody(), BodyType.SENHA_INVALIDA);
	}
	
	/**
	 * 
	 */
	@Test
    @DisplayName("Quinto Teste: Processando a execução com o cartão inexistente")
	@Order(5)
	public void executeCartaoInexistenteTest() {
		 final Cartao cartao = Cartao.builder()
	                .numeroCartao("0100112122223353")
	                .senha(1234)
	                .saldo(BigDecimal.valueOf(15))
	                .build();
		
		final Transacao entity = getTransacaoBusiness().execute(cartao);
		
		Assertions.assertEquals(entity.getBody(), BodyType.CARTAO_INEXISTENTE);
	}
	
	/**
	 * 
	 */
	@Test
    @DisplayName("Sexto Teste: Processando a execução com o cartão inexistente")
	@Order(6)
	public void executeSaldoInsuficienteTest() {
		 final Cartao cartao = Cartao.builder()
	                .numeroCartao("0000111122223333")
	                .senha(1234)
	                .saldo(BigDecimal.valueOf(510))
	                .build();
		
		final Transacao entity = getTransacaoBusiness().execute(cartao);
		
		Assertions.assertEquals(entity.getBody(), BodyType.SALDO_INSUFICIENTE);
	}

}
