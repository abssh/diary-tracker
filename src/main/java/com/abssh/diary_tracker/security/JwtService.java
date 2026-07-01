package com.abssh.diary_tracker.security;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    public String generateToken(UUID userId) {
        return Jwts.builder()
            .subject(userId.toString())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
            .signWith(getSigningKey())
            .compact();
    }

    public UUID extractUserId(String token) {
        String userIdString = exctractAllClaims(token).getSubject();
        return UUID.fromString(userIdString);
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = exctractAllClaims(token).getExpiration();
        return expirationDate.before(new Date());
    }

    public boolean isTokenValid(String token, UUID userId) {
        UUID expectedUserId = extractUserId(token);
        return expectedUserId.equals(userId) && !isTokenExpired(token);
    }

    private Claims exctractAllClaims(String token) {
        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] keyByte = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
