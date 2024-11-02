/**
 * 
 */
package br.com.vr.autorizador.entities;

import br.com.vr.autorizador.enums.BodyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

	/**
	 * 
	 */
	private Integer statusCode;
	
	/**
	 * 
	 */
	private BodyType body;
}
