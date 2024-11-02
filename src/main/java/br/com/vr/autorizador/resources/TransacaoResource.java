/**
 * 
 */
package br.com.vr.autorizador.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.autorizador.business.TransacaoBusiness;
import br.com.vr.autorizador.entities.Cartao;
import jakarta.validation.Valid;
import lombok.Getter;

/**
 * 
 */
@RestController
public class TransacaoResource {

	/**
	 * 
	 */
	@Getter
	@Autowired
	private TransacaoBusiness transacaoBusiness;
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	@PostMapping(value = "/transacoes")
	public ResponseEntity<?> processInsert(@Valid @RequestBody final Cartao entity) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(getTransacaoBusiness().execute(entity));
	} 
	
}
