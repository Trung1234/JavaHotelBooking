package com.example.hotel.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "secretkey";

    public static String generateToken(String username,String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * Method to verify and parse a JWT token
     * @param token
     * @return
     */
    public static Claims verifyToken(String token) {
        try {
            // Parse and verify the token
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            return claims; // If successful, return the claims
        } catch (Exception e) {
            // Handle token parsing or validation failure (e.g., expired token, invalid signature)
            System.out.println("Invalid token: " + e.getMessage());
            return null;
        }
    }

    // Get user and role from claims
    public static String getUsername(Claims claims) {
        return claims.getSubject();  // This is the username or user ID
    }

    public static String getRole(Claims claims) {
        return claims.get("role", String.class);  // Extract role from claims
    }

    public static boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public static String getSubject(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}