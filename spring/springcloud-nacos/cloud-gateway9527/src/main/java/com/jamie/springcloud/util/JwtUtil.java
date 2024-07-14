package com.jamie.springcloud.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.util.*;

public class JwtUtil {
    private static final String SECRET = "abc123";
    private static final String ISSURE = "ddd123";
    private static final long EXPIRATION_TIME = 8 * 60 * 60 * 1000L;
    private static final String TOKEN_PREFIX = "";

    /**
     * 签发JWT
     */
    public static String generateToken(Long id, String username) {
        Map<String, Object> claims = new HashMap<>(16);
        return TOKEN_PREFIX + Jwts.builder()
                .signWith( SignatureAlgorithm.HS512, SECRET )
                .setClaims( claims )
                .setIssuer(ISSURE)
                .setIssuedAt(new Date())
                .setId(id.toString())
                .setSubject(username)
                .setExpiration( new Date( Instant.now().toEpochMilli() + EXPIRATION_TIME  ) )
                .compact();
    }

    /**
     * 解析JWT
     */
    public static Claims getClaimsFromToken(String token){
        Claims claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey( SECRET )
                    .parseClaimsJws( token )
                    .getBody();
        } catch (ExpiredJwtException e){
            System.out.println("token已过期：{}" + token);
        }
        return claims;
    }

    public static void main(String[] args) {
        String token = generateToken(1L, "tom");
        System.out.println(token);
        Claims claims = getClaimsFromToken(token);
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
    }
}