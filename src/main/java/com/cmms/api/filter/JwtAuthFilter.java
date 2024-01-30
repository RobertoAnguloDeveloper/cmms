package com.cmms.api.filter;

import com.cmms.api.services.JpaUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;

public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final JpaUserDetailsService userDetailsService;
    private final Key secretKey;

    public JwtAuthFilter(JpaUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        // Esta clave secreta debe mantenerse segura y no debe compartirse públicamente.
        this.secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
    }

}
