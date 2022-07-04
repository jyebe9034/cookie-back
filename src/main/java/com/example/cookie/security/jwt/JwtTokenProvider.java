package com.example.cookie.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements Serializable {

    private static final long serialVersionUID = 1L;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token-valid-time}")
    private long tokenValidTime; // 60 * 60 * 26 = 86400 (1일)

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * JWT 토큰 생성
     * @param claims
     */
    public String setToken(Map<String, Object> claims) {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS512");

        long now = System.currentTimeMillis();
        claims.put("iat", new Date(now));
        claims.put("exp", new Date(now + tokenValidTime * 1000));

        return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
    }

    /**
     * JWT Body 변환
     * @param jwt
     * @return
     */
    public Claims getClaims(String jwt) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwt).getBody();
    }

    /**
     * JWT 검증
     * @param claims
     */
    public boolean validateJwt(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
