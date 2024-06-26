package br.com.jeff.securityTraining.service.impl;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceIMPL {
    
    private String generetadToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date (System.currentTimeMillis() + 1000 * 60 * 24))
        .signWith(getSiginKey() , SignatureAlgorithm.HS256)
        .compact();
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim (String token, Function<Claims, T> claimsResolver ){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims); 
    }


    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSiginKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSiginKey() {
        byte[] key = Decoders.BASE64.decode("DWJIADOIJWAIFUKHWFJUAWBDAW1D5AW1D45AS4D0AW2DAWDLAWKS0");
        return Keys.hmacShaKeyFor(key);
    }
}
