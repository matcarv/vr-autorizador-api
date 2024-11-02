/**
 * 
 */
package br.com.vr.autorizador.business;

import br.com.vr.autorizador.entities.Cartao;
import br.com.vr.autorizador.entities.Transacao;

/**
 * 
 */
public interface TransacaoBusiness {

	/**
	 * 
	 * @param cartao
	 */
	public Transacao execute(final Cartao cartao);
}
