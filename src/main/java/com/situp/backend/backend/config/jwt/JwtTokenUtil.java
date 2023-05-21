package com.situp.backend.backend.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.situp.backend.backend.database.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class JwtTokenUtil {
    final private static Logger LOG = LoggerFactory.getLogger(JwtTokenUtil.class);

    final public static String CLAIM_PAYLOAD = "payload";
    final private Locale locale = Locale.FRANCE;

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.token-duration}")
    private int tokenDuration;

    Algorithm standardAlgorithm, refreshAlgorithm;

    @PostConstruct
    private void init() {
        standardAlgorithm = Algorithm.HMAC256(secret);
    }

    public DecodedJWT decodeToken(String token) throws JWTVerificationException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build(); // Reusable verifier instance
            return verifier.verify(token);
        } catch (Throwable e) {
            LOG.error("could not decode token", e);
        }
        throw new JWTVerificationException("invalid token");
    }

    public TokenPayload getPayload(DecodedJWT jwt) {
        String payload = jwt.getClaim(CLAIM_PAYLOAD).asString();
        try {
            return new ObjectMapper().readValue(payload, TokenPayload.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateToken(User user) {
        TokenPayload tokenPayload = new TokenPayload(user.getId(), new Date(), user.isAdmin());
        Calendar calendar = Calendar.getInstance(locale); // gets a calendar using the default time zone and locale.
        calendar.add(Calendar.SECOND, tokenDuration);

        try {
            String payload = new ObjectMapper().writeValueAsString(tokenPayload);
            return JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(Calendar.getInstance(locale).getTime())
                    .withExpiresAt(calendar.getTime())
                    .withClaim(CLAIM_PAYLOAD, payload)
                    .sign(standardAlgorithm);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}