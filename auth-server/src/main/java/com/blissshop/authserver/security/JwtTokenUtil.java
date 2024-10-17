package com.blissshop.authserver.security;

import com.blissshop.authserver.common.JwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpirationInMillis;

    // Tạo JWT từ thông tin người dùng
    public String generateToken(UserDetails userDetails) {
        // Lấy tên người dùng
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtExpirationInMillis);

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .id(userDetails.getUsername())
                .claim(JwtConstant.USER_NAME, userDetails.getUsername())
                .claim(JwtConstant.ROLE, userDetails.getAuthorities())
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        if (StringUtils.isBlank(secret)) {
            throw new IllegalArgumentException("The secret key for JWT is required!!!");
        }
        //pad 64 bytes(512 bits)
        return Keys.hmacShaKeyFor(StringUtils.rightPad(secret, 64, StringUtils.SPACE).getBytes());
    }

    // Trích xuất username từ JWT
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Kiểm tra token đã hết hạn chưa
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    }

    // Xác thực token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}