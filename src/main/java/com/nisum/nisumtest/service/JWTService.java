package com.nisum.nisumtest.service;


import com.nisum.nisumtest.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    //LLAVE SECRETA ES: LLAVE_SECRETA_NIVEL_1_PARA_PRUEBA_TECNICA_NISUM [Base64]
    private static final String JWT_PUBLIC_KEY = "TExBVkVfU0VDUkVUQV9OSVZFTF8xX1BBUkFfUFJVRUJBX1RFQ05JQ0FfTklTVU0=";
    private static final Long JWT_ENABLE_TIME = 1000 * 60 * 60 * (long) 8;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        //var rol = user.getAuthorities().stream().collect(Collectors.toList()).get(0);
        //claims.put("rol", rol);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_ENABLE_TIME) )
                .signWith(SignatureAlgorithm.HS256, JWT_PUBLIC_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(JWT_PUBLIC_KEY).build().parseSignedClaims(token).getBody();
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, User user)
    {
        final String username = extractUsername(token);
        return (username.equals(user.getEmail()) && !isTokenExpired(token));
    }

}

