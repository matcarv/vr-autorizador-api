/**
 * 
 */
package br.com.vr.autorizador.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	/**
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withUsername("admin")
				.password(passwordEncoder().encode("vr@123**"))
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
	
	/**
	 * 
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
    protected SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
		 http
         .csrf(CsrfConfigurer::disable)
         .authorizeHttpRequests((authorize) -> authorize
             .requestMatchers(
                     "/",
                     "/docs",
                     "/v2/api-docs/**",        // swagger
                     "/webjars/**",            // swagger-ui webjars
                     "/swagger-resources/**",  // swagger-ui resources
                     "/configuration/**",      // swagger configuration
                     "/swagger-ui/**",
                     "/*.html",
                     "/favicon.ico",
                     "/**/*.html",
                     "/**/*.css",
                     "/**/*.js"
             ).permitAll()
             .anyRequest().authenticated()
         )
         .httpBasic(Customizer.withDefaults());

		 return http.build();
	}
	
	/**
	 * 
	 * @return
	 */
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
