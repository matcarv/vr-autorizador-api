package br.com.vr.autorizador.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 
 * @author matos.weslley
 *
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableBusinessException extends ResponseStatusException {

	/**
	 * 
	 */
    private static final long serialVersionUID = -5745244507349469157L;
	
	 /**
     * 
     */
	public UnprocessableBusinessException(final String message) {
		super(HttpStatus.UNPROCESSABLE_ENTITY, message, new Throwable(message));
	}

}
