
package com.civicops.complaint.config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Configuration
public class SecurityConfig {

	@Bean
	public SecretKey jwtSecretKey(@Value("${jwt.secret}") String secret) {

		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}

	@Bean
	public JwtDecoder jwtDecoder(SecretKey jwtSecretKey) {

		int keyLength = jwtSecretKey.getEncoded().length;

		MacAlgorithm algorithm;

		if (keyLength >= 64) {
			algorithm = MacAlgorithm.HS512;
		} else if (keyLength >= 48) {
			algorithm = MacAlgorithm.HS384;
		} else {
			algorithm = MacAlgorithm.HS256;
		}

		return NimbusJwtDecoder.withSecretKey(jwtSecretKey).macAlgorithm(algorithm).build();
	}

	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {

		JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();

		authoritiesConverter.setAuthoritiesClaimName("role");
		authoritiesConverter.setAuthorityPrefix("ROLE_");

		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

		converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

		return converter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,
			JwtAuthenticationConverter jwtAuthenticationConverter) throws Exception {

		http.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(auth -> auth.requestMatchers("/actuator/health").permitAll()

						// Only officers and admins can update complaint status.
						.requestMatchers(org.springframework.http.HttpMethod.PATCH, "/api/complaints/*/status")
						.hasAnyRole("OFFICER", "ADMIN")

						// Other complaint endpoints require authentication.
						.requestMatchers("/api/complaints/**").authenticated()

						.anyRequest().authenticated())

				.oauth2ResourceServer(
						oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));

		return http.build();
	}
}
