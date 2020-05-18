package com.goonmeonity.domain.service.auth;

import com.goonmeonity.domain.entity.user.User;
import com.goonmeonity.domain.service.auth.error.TokenHasExpiredError;
import com.goonmeonity.domain.service.auth.error.TokenIsInvalidError;
import com.goonmeonity.domain.service.user.dto.UserInfo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenProvider {
    private JwtTokenProvider(){}

    private static class InnerInstance{
        private static final JwtTokenProvider instance = new JwtTokenProvider();
    }

    public static JwtTokenProvider getInstance(){
        return InnerInstance.instance;
    }

    public String generateAccessKey(User user, String secretKey, int expirationDate){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId().toString());

        return generateAccesskey(claims, secretKey, expirationDate);
    }

    private String generateAccesskey(Map<String, Object> claims, String secretKey,int expirationDate){
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject("AccessToken")
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(getSigningKey(secretKey))
                .compact();
    }

    public String generateRefreshToken(User user, String secretKey){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId().toString());

        return generateRefreshToken(claims, secretKey);
    }

    private String generateRefreshToken(Map<String, Object> claims, String secretKey){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject("RefreshToken")
                .setIssuedAt(new Date())
                .signWith(getSigningKey(secretKey))
                .compact();
    }

    public long getUserIdByClaims(Claims claims, String subject){
        if(!(claims.getSubject().equals(subject)))
            throw new TokenIsInvalidError("token subject do not match.");

        return Long.parseLong(claims.get("userId").toString());
    }

    public Claims decodingToken(String token, String secretKey){
        try {
            return Jwts.parser()
                    .setSigningKey(getSigningKey(secretKey))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException e) {
            throw new TokenIsInvalidError();
        } catch (ExpiredJwtException e) {
            throw new TokenHasExpiredError();
        } catch (UnsupportedJwtException e) {
            throw new TokenIsInvalidError("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            throw new TokenIsInvalidError("JWT claims string is empty.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new TokenIsInvalidError();
        }
    }

    private SecretKey getSigningKey(String secretKey){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
