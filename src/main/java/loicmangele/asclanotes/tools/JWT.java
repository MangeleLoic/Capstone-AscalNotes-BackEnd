package loicmangele.asclanotes.tools;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import loicmangele.asclanotes.exceptions.UnauthorizedException;
import loicmangele.asclanotes.utente.Utente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWT {
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Utente utente) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .subject(String.valueOf(utente.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(token);
        } catch (Exception e) {
            throw new UnauthorizedException("Problemi con il token!");
        }
    }

   /* public String getIdFromToken(String accessToken){
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build().parseSignedClaims(accessToken).getPayload().getSubject();
    }*/

}