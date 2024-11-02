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
import org.springframework.test.annotation.Rollback;

import br.com.vr.autorizador.business.CartaoBusiness;
import br.com.vr.autorizador.entities.Cartao;
import br.com.vr.autorizador.exceptions.NotFoundBusinessException;
import br.com.vr.autorizador.exceptions.UnprocessableBusinessException;
import lombok.Getter;

/**
 * 
 */
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartaoBusinessTest {

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
		
	}
	
	/**
	 * 
	 */
	@Test
    @DisplayName("Primeiro Teste: Processando a inclusáo do Cartão")
	@Order(1)
	@Rollback(value = false)
    public void processInsertTest(){
        final Cartao entity = Cartao.builder()
                .numeroCartao("1234567812345678")
                .senha(1234)
                .saldo(BigDecimal.valueOf(500))
                .build();

        final Cartao merged = getCartaoBusiness().processInsert(entity);

        Assertions.assertTrue(merged != null);
    }
	
	/**
	 * 
	 */
	@Test
    @DisplayName("Segundo Teste: Localizando o cartão pelo número")
	@Order(2)
    public void findByNumeroCartaoTest(){
		final Cartao entity = getCartaoBusiness().findByNumeroCartao("1234567812345678");
		
        Assertions.assertEquals(entity.getNumeroCartao(), "1234567812345678");
    }
	
	/**
	 * 
	 */
	@Test
    @DisplayName("Terceiro Teste: Retornando a mensagem de Cartão não encontrado")
	@Order(3)
    public void findByNumeroErroCartaoNaoEncontradoTest(){
		final NotFoundBusinessException ex = 
				Assertions.assertThrows(NotFoundBusinessException.class, 
        				()-> { 
        					getCartaoBusiness().findByNumeroCartao("12345678123456789");
        				}
	        	);
	        
		Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), ex.getStatusCode().value());
	        
    }
	
	@Test
    @DisplayName("Quarto Teste: Retornando a mensagem de Cartão já cadastrado")
	@Order(4)
    public void processInsertErroCartaoCadastradoTest(){
        final Cartao entity = Cartao.builder()
                .numeroCartao("1234567812345678")
                .senha(1234)
                .saldo(BigDecimal.valueOf(500))
                .build();

	        
        final UnprocessableBusinessException ex = 
        		Assertions.assertThrows(UnprocessableBusinessException.class, 
        				()-> { 
        					getCartaoBusiness().processInsert(entity);
        				}
        		);
        
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getStatusCode().value());
    }
	
	@Test
    @DisplayName("Quinto Teste: Retornando o Saldo do Cartão")
	@Order(5)
    public void findSaldoByNumeroCartaoTest(){
	    final Cartao entity = Cartao.builder()
                .numeroCartao("0123456789123456")
                .senha(1234)
                .saldo(BigDecimal.valueOf(485))
                .build();

        getCartaoBusiness().processInsert(entity);
        
		final BigDecimal saldo = getCartaoBusiness().findSaldoByNumeroCartao("0123456789123456");
        
		Assertions.assertEquals(saldo, BigDecimal.valueOf(485));
    }
	
}
