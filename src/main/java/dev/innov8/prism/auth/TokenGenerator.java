package dev.innov8.prism.auth;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {

    private final JwtConfig jwtConfig;

    @Autowired
    public TokenGenerator(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createToken(Principal subject) {

        long now = System.currentTimeMillis();

        JwtBuilder tokenBuilder = Jwts.builder()
                                      .setId(subject.getOrgId())
                                      .setSubject(subject.getOrgName())
                                      .setIssuer("prism")
                                      .setIssuedAt(new Date(now))
                                      .claim("authCode", subject.getAuthCode())
                                      .setExpiration(new Date(now + jwtConfig.getExpiration()))
                                      .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return tokenBuilder.compact();

    }


}
