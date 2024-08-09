package com.coolReaders.library_managment_system.filters ; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.coolReaders.library_managment_system.security.JwtUtil;

import io.jsonwebtoken.Claims;

import java.util.Arrays;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
      
        Cookie[] cookies = request.getCookies() != null ? request.getCookies() : new Cookie[0];
        String authToken = Arrays.stream(cookies)
            .filter(cookie -> "authToken".equals(cookie.getName()))
            .findFirst()
            .map(Cookie::getValue)
            .orElse(null);

        if (authToken == null || !jwtUtil.validateToken(authToken)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
            return;
        }

        Claims claims = jwtUtil.extractClaims(authToken);
        if (claims != null) {
            request.setAttribute("userId", claims.get("id").toString());
    }

        chain.doFilter(request, response);
    }
}