/**
 * 
 */
package br.com.vr.autorizador.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.vr.autorizador.entities.Cartao;
import br.com.vr.autorizador.entities.Transacao;
import br.com.vr.autorizador.enums.BodyType;
import br.com.vr.autorizador.exceptions.NotFoundBusinessException;
import lombok.Getter;

/**
 * 
 */
@Component
@Transactional(propagation = Propagation.SUPPORTS)
public class TransacaoBusinessImpl implements TransacaoBusiness {

	/**
	 * 
	 */
	@Getter
	@Autowired
	private CartaoBusiness cartaoBusiness;
	
	/**
	 * 
	 * @param cartao
	 */
	@Override
	public Transacao execute(final Cartao cartao) {
		final Transacao entity = processValidate(new Transacao(HttpStatus.CREATED.value(), BodyType.OK), cartao);
		
		switch (entity.getBody()) {
		case OK: {
			getCartaoBusiness().processUpdateSaldo(cartao);
			break;
		}
		default:
			
		}
		
		return entity;
	}
	
	/**
	 * 
	 * @param entity
	 * @param cartao
	 * @return
	 */
	private Transacao processValidate(final Transacao entity, final Cartao cartao) {
		try {
			final Cartao persisted = getCartaoBusiness().findByNumeroCartao(cartao.getNumeroCartao());
			
			if(!persisted.getSenha().equals(cartao.getSenha())) {
				entity.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
				entity.setBody(BodyType.SENHA_INVALIDA);
				
			} else if(persisted.getSaldo().doubleValue() < cartao.getSaldo().doubleValue()) {
				entity.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
				entity.setBody(BodyType.SALDO_INSUFICIENTE);
				
			}
		} catch(NotFoundBusinessException e) {
				entity.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
				entity.setBody(BodyType.CARTAO_INEXISTENTE);
		}
		
		return entity;
	}
}
