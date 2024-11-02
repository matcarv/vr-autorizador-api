package br.com.vr.autorizador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author matos.weslley
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
@EntityScan( basePackages = {"br.com.vr.autorizador.entities"})
@EnableJpaRepositories(basePackages = { "br.com.vr.autorizador.repository"})
@ComponentScan(basePackages = {"br.com.vr.autorizador"})
public class VrAutorizadorApiApplication {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(VrAutorizadorApiApplication.class, args);
	}

}
