package dev.innov8.prism.auth;

import dev.innov8.prism.common.exceptions.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenValidator {

    private final JwtConfig jwtConfig;

    @Autowired
    public TokenValidator(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public Optional<Principal> parseToken(String token) {

        try {

            Claims claims = Jwts.parser()
                                .setSigningKey(jwtConfig.getSigningKey())
                                .parseClaimsJws(token)
                                .getBody();

            return Optional.of(new Principal(claims.getId(), claims.getSubject(), claims.get("authCode", String.class)));

        } catch (Exception e) {
            throw new InvalidTokenException(e.getMessage());
        }

    }

}
