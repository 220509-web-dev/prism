package dev.innov8.prism.auth;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

@Component
public class JwtConfig {

    @Value("${jwt.secret}")
    private String salt;

    @Value("#{1 * 60 * 60 * 1000}") // number of milliseconds in an hour
    private int expiration;

    private final SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;

    private Key signingKey;

    @PostConstruct
    public void createSigningKey() {
        byte[] saltyBytes = DatatypeConverter.parseBase64Binary(salt);
        signingKey = new SecretKeySpec(saltyBytes, sigAlg.getJcaName());
    }

    public int getExpiration() {
        return expiration;
    }

    public SignatureAlgorithm getSigAlg() {
        return sigAlg;
    }

    public Key getSigningKey() {
        return signingKey;
    }

}
