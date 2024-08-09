package com.coolReaders.library_managment_system.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private String secretKey = "HazemmosaddHazemmosaddHazemmosaddHazemmosadd"; 
    private Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    public Claims extractClaims(String jwt) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
        try {
            Jws<Claims> jwsClaims = jwtParser.parseClaimsJws(jwt);
            return jwsClaims.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateToken(String jwt) {
        try {
            extractClaims(jwt); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
