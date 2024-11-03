package br.com.vr.autorizador.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import br.com.vr.autorizador.exceptions.NotFoundBusinessException;
import br.com.vr.autorizador.exceptions.UnprocessableBusinessException;

/**
 * 
 * @author weslleymatosdecarvalho
 *
 */
@ControllerAdvice
public class MicroserviceExceptionHandler {

	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({
		NotFoundBusinessException.class, 
		UnprocessableBusinessException.class })
    public final ResponseEntity<?> handleException(final ResponseStatusException ex, final WebRequest request) {
		return ResponseEntity
                .status(ex.getStatusCode())
                .body(ex.getReason());
	}
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<?> handleException(final MethodArgumentNotValidException ex) {
		return ResponseEntity
                .status(ex.getStatusCode())
                .body(ex.getBindingResult()
                		.getAllErrors()
                		.stream().map(e -> { 
                			return e.getDefaultMessage(); 
                		})
                	);
	 }
	
}
