package br.com.vr.autorizador.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@Entity
@Builder
@Table(name = "CARTAO")
@NoArgsConstructor
@AllArgsConstructor
public class Cartao {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "ID", nullable = false)
	private String id;
	
	/**
	 * 
	 */
	@NotBlank(message = "Número do Cartão é requerido.")
	@Column(name = "NUMERO_CARTAO", nullable = false)
	private String numeroCartao;
	
	/**
	 * 
	 */
	@NotNull(message = "Senha é requerido.")
	@Column(name = "SENHA", nullable = false)
	private Integer senha;

	/**
	 * 
	 */
	@NotNull(message = "Saldo é requerido.")
	@Column(name = "SALDO", nullable = false, precision = 6, scale = 2)
	private BigDecimal saldo;
}
