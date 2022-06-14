package com.marketProject.configuration.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    public static final Long EXPIRATION_MS = 60 * 60 * 1000L; //1시간
//    @Value("spring.jwt.secretKey") 왜 nullpoint나는지 확인 필요,,,
    private static String secreteKey = "market-project";
    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secreteKey = Base64.getEncoder().encodeToString(secreteKey.getBytes());
    }

    // token 생성
    public static String generateToken(String userPk, List<String> roles) {
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + (EXPIRATION_MS));

        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secreteKey)
                .compact();
    }

    // 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secreteKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validationToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secreteKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }

    }
}