
package com.civicops.auth.controller;

import com.civicops.auth.dto.LoginRequest;
import com.civicops.auth.dto.LoginResponse;
import com.civicops.auth.dto.RegisterRequest;
import com.civicops.auth.entity.User;
import com.civicops.auth.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping("/test-protected")
	public ResponseEntity<?> testProtected(Authentication authentication) {

		return ResponseEntity.ok(Map.of("message", "JWT authentication successful", "email", authentication.getName()));
	}

	@GetMapping("/test-citizen")
	public ResponseEntity<?> testCitizen(Authentication authentication) {
		return ResponseEntity.ok(Map.of("message", "Citizen access granted", "email", authentication.getName()));
	}

	@GetMapping("/test-officer")
	public ResponseEntity<?> testOfficer(Authentication authentication) {
		return ResponseEntity.ok(Map.of("message", "Officer access granted", "email", authentication.getName()));
	}

	@GetMapping("/test-admin")
	public ResponseEntity<?> testAdmin(Authentication authentication) {
		return ResponseEntity.ok(Map.of("message", "Admin access granted", "email", authentication.getName()));
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {

		try {
			User user = authService.register(request);

			Map<String, Object> response = Map.of("message", "Registration successful", "id", user.getId(), "fullName",
					user.getFullName(), "email", user.getEmail(), "role", user.getRole());

			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		} catch (IllegalArgumentException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", ex.getMessage()));
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {

		try {
			LoginResponse response = authService.login(request);

			return ResponseEntity.ok(response);

		} catch (IllegalArgumentException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", ex.getMessage()));
		}
	}

}
