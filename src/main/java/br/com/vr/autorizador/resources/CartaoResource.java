/**
 * 
 */
package br.com.vr.autorizador.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.autorizador.business.CartaoBusiness;
import br.com.vr.autorizador.entities.Cartao;
import jakarta.validation.Valid;
import lombok.Getter;

/**
 * 
 */
@RestController
public class CartaoResource {

	/**
	 * 
	 */
	@Getter
	@Autowired
	private CartaoBusiness cartaoBusiness;
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping(value = "/cartoes")
	public ResponseEntity<?> processInsert(@Valid @RequestBody final Cartao entity) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(getCartaoBusiness().processInsert(entity));
	}
	
	/**
	 * 
	 * @param numeroCartao
	 * @return
	 */
	@GetMapping(value = "/cartoes/{numeroCartao}")
	public ResponseEntity<?> findSaldoByNumeroCartao(@PathVariable(name = "numeroCartao") final String numeroCartao) {
		return ResponseEntity.ok(getCartaoBusiness().findSaldoByNumeroCartao(numeroCartao));
	}
}
