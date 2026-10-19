
package com.civicops.auth.service;

import com.civicops.auth.dto.LoginRequest;
import com.civicops.auth.dto.LoginResponse;
import com.civicops.auth.dto.RegisterRequest;
import com.civicops.auth.entity.User;
import com.civicops.auth.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public User register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "Email is already registered");
        }

        User user = new User();
        user.setFullName(request.getFullName().trim());
        user.setEmail(request.getEmail().trim().toLowerCase());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Role is assigned by the server, not by the client.
        user.setRole("CITIZEN");

        return userRepository.save(user);
    }
    

@Transactional(readOnly = true)
public LoginResponse login(LoginRequest request) {

    String email = request.getEmail().trim().toLowerCase();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException(
                    "Invalid email or password"));

    boolean passwordMatches = passwordEncoder.matches(
            request.getPassword(),
            user.getPassword()
    );
    
    if (!passwordMatches) {
        throw new IllegalArgumentException(
                "Invalid email or password");
    }
    
 // Generate JWT after successful authentication
    String token = jwtService.generateToken(
            user.getEmail(),
            user.getRole());

    // We'll return this token through the controller in the next step.

    return new LoginResponse(
            "Login successful",
            token,
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getRole());
}

}
