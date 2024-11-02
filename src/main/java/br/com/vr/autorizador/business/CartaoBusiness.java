/**
 * 
 */
package br.com.vr.autorizador.business;

import java.math.BigDecimal;

import br.com.vr.autorizador.entities.Cartao;

/**
 * 
 */
public interface CartaoBusiness{

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public Cartao processInsert(final Cartao entity);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public Cartao processUpdateSaldo(final Cartao entity);
	
	/**
	 * 
	 * @param numeroCartao
	 * @return
	 */
	public Cartao findByNumeroCartao(final String numeroCartao);
	
	/**
	 * 
	 * @param numeroCartao
	 * @return
	 */
	public BigDecimal findSaldoByNumeroCartao(final String numeroCartao);
}
