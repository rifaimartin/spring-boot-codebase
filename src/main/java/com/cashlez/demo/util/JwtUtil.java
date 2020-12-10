package com.cashlez.demo.util;

import com.cashlez.demo.model.Merchant;
import com.cashlez.demo.model.User;
import com.cashlez.demo.security.JwtProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtil {
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(JwtProperty.SECRET).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(User user, String userId) {
        return createToken(user,  userId);
    }

    private String createToken(User user, String userId) {
        Claims claims = Jwts.claims().setSubject(user.getUserName());
//        claims.put("scopes", userDetails.getAuthorities());
        claims.setId(userId);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUserName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, JwtProperty.SECRET).compact();
    }

    public Date getExpiration(String type) {
        switch (type) {
            case "WEB": return new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public boolean isTokenValid(String token, Merchant merchant) {
        final String username = extractUsername(token);
        return (username.equals(merchant.getUserName()) && !isTokenExpired(token));
    }
}
