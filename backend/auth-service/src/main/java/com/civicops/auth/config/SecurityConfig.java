
package com.civicops.auth.config;

import com.civicops.auth.filter.JwtAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(exception -> exception
					    .authenticationEntryPoint((request, response, authException) ->
					        response.sendError(
					            HttpStatus.UNAUTHORIZED.value(),
					            "Authentication required"
					        )
					    )
					    .accessDeniedHandler((request, response, accessDeniedException) ->
					        response.sendError(
					            HttpStatus.FORBIDDEN.value(),
					            "Access denied"
					        )
					    )
					)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/auth/register", "/api/auth/login", "/actuator/health").permitAll()

						.requestMatchers("/api/auth/test-admin").hasRole("ADMIN")

						.requestMatchers("/api/auth/test-officer").hasRole("OFFICER")

						.requestMatchers("/api/auth/test-citizen").hasRole("CITIZEN")

						.anyRequest().authenticated())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
