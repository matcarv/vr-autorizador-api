package br.com.vr.autorizador.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 
 * @author matos.weslley
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundBusinessException extends ResponseStatusException {

	/**
	 * 
	 */
    private static final long serialVersionUID = -5745244507349469157L;
	
	 /**
     * 
     */
	public NotFoundBusinessException(final String message) {
		super(HttpStatus.NOT_FOUND, message, new Throwable(message));
	}

}
