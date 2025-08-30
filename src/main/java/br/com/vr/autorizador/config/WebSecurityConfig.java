/**
 * 
 */
package br.com.vr.autorizador.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                .csrf(csrf -> csrf.ignoringRequestMatchers(
                        AntPathRequestMatcher.antMatcher("/"),
                        AntPathRequestMatcher.antMatcher("/docs"),
                        AntPathRequestMatcher.antMatcher("/v2/api-docs/**"),
                        AntPathRequestMatcher.antMatcher("/webjars/**"),
                        AntPathRequestMatcher.antMatcher("/swagger-resources/**"),
                        AntPathRequestMatcher.antMatcher("/configuration/**"),
                        AntPathRequestMatcher.antMatcher("/swagger-ui/**"),
                        AntPathRequestMatcher.antMatcher("/*.html"),
                        AntPathRequestMatcher.antMatcher("/favicon.ico"),
                        AntPathRequestMatcher.antMatcher("/**/*.html"),
                        AntPathRequestMatcher.antMatcher("/**/*.css"),
                        AntPathRequestMatcher.antMatcher("/**/*.js")
                )).csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(
                                        "/v2/api-docs/**",
                                        "/swagger-ui/**",
                                        "/swagger-resources/**",
                                        "/webjars/**",
                                        "/configuration/**",
                                        "/",
                                        "/*.html",
                                        "/favicon.ico",
                                        "/**/*.html",
                                        "/**/*.css",
                                        "/**/*.js",
                                        "/docs"
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
