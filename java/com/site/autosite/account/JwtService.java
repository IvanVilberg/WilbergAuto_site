package com.site.autosite.account;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    

    private final String SECRET_KEY = "15b5a78c919f44013b32bdeee190de4345bccebdeec9368b852dff006e99191d";

    public String extractLogin(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails account){
        String login = extractLogin(token);
        return login.equals(account.getUsername()) && isTokenExpired(token); 
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    
    public <T> T extractClaim(String token, Function<Claims, T> resolver){
        Claims claims = extractAlClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAlClaims(String token) {
        return Jwts
            .parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public String generateToken(Account account){
        String token = Jwts
            .builder()
            .subject(account.getLogin())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
            .signWith(getSignInKey())
            .compact();
        
        return token;
    }

    private SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
