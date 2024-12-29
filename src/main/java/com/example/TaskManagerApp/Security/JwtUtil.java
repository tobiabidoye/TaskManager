package com.example.TaskManagerApp.Security;
import java.security.Key;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import java.util.List;
@Component
public class JwtUtil {
    private final String SECRET_KEY = System.getenv("JWT_SECRET");
    private final int TOKEN_VALIDITY = 3600 * 1000; //token lasts for an hour before user has to log in again
    public String generateToken(String username, List<String> roles){
        System.out.println(SECRET_KEY);
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                    .subject(username)
                    .claim("roles", roles)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY)) 
                    .signWith(key)
                    .compact();
    }

    public String extractUsername(String token){ 
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);
        try{
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        }catch(Exception e){
            System.err.println("Token processing error in jwt util extract username" + e.getMessage());
            throw e;
        }
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

    public <T> T extractClaim(String token, String claimKey){ 
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes); 
       try{ 
        Claims claims  = Jwts.parser()
                            .verifyWith(secretKey)
                            .build()
                            .parseSignedClaims(token)

                            .getPayload();
        System.out.println("Extracted Claims: " + claims);
        return (T) claims.get(claimKey);
       }catch (Exception e){
            System.err.println("Error extracting claim in extract claim jwt util" + claimKey + " " + e.getMessage());
            throw e;
       }
    }
}
