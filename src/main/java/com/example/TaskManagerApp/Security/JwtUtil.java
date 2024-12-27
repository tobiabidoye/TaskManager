package com.example.TaskManagerApp.Security;
import java.security.Key;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private final String SECRET_KEY = System.getenv("JWT_SECRET"); 
    private final int TOKEN_VALIDITY = 3600 * 1000; //token lasts for an hour before user has to log in again

    public String generateToken(String username){
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                    .subject(username)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY)) 
                    .signWith(key)
                    .compact();
    }

    public String extractUsername(String token){ 
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }
    
    public Boolean isTokenExpired(String token){ 
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);
        Date expiration = Jwts.parser()
                                .verifyWith(secretKey)
                                .build()
                                .parseSignedClaims(token)
                                .getPayload()
                                .getExpiration();
        return expiration.before(new Date());
                    
    }
    public Boolean validateToken(String token, String Username){ 
        return extractUsername(token).equals(Username) && !(isTokenExpired(token));
    }
}
