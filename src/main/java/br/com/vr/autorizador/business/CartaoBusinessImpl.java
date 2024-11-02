/**
 * 
 */
package br.com.vr.autorizador.business;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.vr.autorizador.entities.Cartao;
import br.com.vr.autorizador.exceptions.NotFoundBusinessException;
import br.com.vr.autorizador.exceptions.UnprocessableBusinessException;
import br.com.vr.autorizador.repository.CartaoRepository;
import lombok.Getter;

/**
 * 
 */
@Component
@Transactional(propagation = Propagation.SUPPORTS)
public class CartaoBusinessImpl implements CartaoBusiness {
	
	/**
	 * 
	 */
	@Getter
	@Autowired
	private CartaoRepository repository;
	
	/**
	 * 
	 */
	@Override
	@Transactional
	public Cartao processInsert(final Cartao entity) {
		getRepository().findByNumeroCartao(entity.getNumeroCartao()).map(e -> {
			throw new UnprocessableBusinessException("Cartão já cadastrado.");
		});
		
		return getRepository().save(entity);
	}
	
	/**
	 * 
	 */
	@Override
	@Transactional
	public Cartao processUpdateSaldo(final Cartao entity) {
		final Cartao persisted = findByNumeroCartao(entity.getNumeroCartao());
		entity.setId(persisted.getId());
		entity.setNumeroCartao(persisted.getNumeroCartao());
		entity.setSenha(persisted.getSenha());
		entity.setSaldo(persisted.getSaldo().subtract(entity.getSaldo()));
		
		return getRepository().save(entity);
	}
	
	/**
	 * 
	 */
	@Override
	public Cartao findByNumeroCartao(final String numeroCartao) {
		return getRepository().findByNumeroCartao(numeroCartao).map(e -> {
			return e;
		}).orElseThrow(() -> new NotFoundBusinessException("Cartão não encontrado."));
	}

	/**
	 * 
	 */
	@Override
	public BigDecimal findSaldoByNumeroCartao(final String numeroCartao) {
		return findByNumeroCartao(numeroCartao).getSaldo();
	}
}
