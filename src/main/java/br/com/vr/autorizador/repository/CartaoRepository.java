/**
 * 
 */
package br.com.vr.autorizador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import br.com.vr.autorizador.entities.Cartao;

/**
 * 
 */
public interface CartaoRepository extends JpaRepository<Cartao, String>, 
												CrudRepository<Cartao, String> {

	/**
	 * 
	 * @param numeroCartao
	 * @return
	 */
	public Optional<Cartao> findByNumeroCartao(final String numeroCartao);
}
