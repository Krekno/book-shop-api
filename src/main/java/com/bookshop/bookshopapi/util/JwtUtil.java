package com.bookshop.bookshopapi.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET = "4140a4374d449fed3fb55447cba8d35b5d489c878c452ee95142eee7a02772cb5dfc70c963b63c36e0277768a2114dd2dc62849a8d71dc4b970b57d63bb8adf874a7133c38b9d95d18ec8da6f3ce32fe813d438b3d8fb5c5da1cb8e4250ba3bed8a6665653fc47b26eecc711761a77579992aaea69dc6b11c4e71dd0996519992a97114fe97f294c3e17a6df53f43c5116cd457cf86733e19c55fed40a55805c6b0bfe611922a3681d39ea251551e2a217128e652186dbe08a02c383f3ff783a6c79fb1c4daf19df857b01a6a8e8d506cb4d77e74d55988cd53ac9c2b8e83353e982599284d14cdf1e5553e243c16883abb3369c9a60dd14b5ddce5d63b6d9bf";
    private static final long EXPIRATION_TIME = 86400000; // 1 day

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}