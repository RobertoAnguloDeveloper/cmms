package com.cmms.api.auth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.cmms.api.auth.response.AuthResponse;
import com.cmms.api.models.SecurityUser;
import com.cmms.api.models.User;
import com.cmms.api.repositories.UserRepository;
import com.cmms.api.services.JwtService;

public class AuthService {
    private final UserRepository repository;
    private final JwtService jwtService;

    private  AuthenticationManager authenticationManager;

    public AuthService(UserRepository repository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse authenticate(SecurityUser securityUser) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    securityUser.getUsername(),
                    securityUser.getPassword()
                )
        );

        User user = repository.findByUsername(securityUser.getUsername()).orElseThrow();

        Map<String, Object> extraclaims = new HashMap<>();

        extraclaims.put("authority", user.getRole().getRoleName());
        String jwtToken = jwtService.generateToken(extraclaims, securityUser);

        return new AuthResponse(jwtToken);
    }

    
}
