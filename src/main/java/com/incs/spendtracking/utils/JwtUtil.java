package com.incs.spendtracking.utils;

import com.incs.spendtracking.common.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// code to generate token and validate token
@Service
public class JwtUtil {

    private final String AUTHORITIES_KEY = "scopes";

    @Value("${jwt.secret}")
    private String secret;

  /*  @Value("")
    private String JWT_VALIDITY;*/

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /*public String extractRoleName(String token) {
        return extractClaim(token, Claims::ge);
    }*/

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username, User user) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username, user);
    }

    private String createToken(Map<String, Object> claims, String subject, User user) {

        claims = Jwts.claims().setSubject(subject);
        claims.put(AUTHORITIES_KEY, "ROLE_" + user.getUserRoleName());

        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
