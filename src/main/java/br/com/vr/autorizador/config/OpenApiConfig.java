package br.com.vr.autorizador.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * 
 */
@Configuration
public class OpenApiConfig {

	/**
	 * 
	 * @return
	 */
	@Bean
	OpenAPI springOpenAPI() {
		return new OpenAPI().info(
			  new Info()
			  	.title("VR Autorizador API")
			  	.description("VR Autorizador API - Uma Solução VR Benefícios")
			  	.version("1.0.0")
			  	.license(new License().name("Weslley Matos de Carvalho")
	    		.url("https://matcarv.com")));
	 }
	
}
