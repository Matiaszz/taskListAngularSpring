package com.tasklist.Domain.User.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tasklist.Domain.User.DTOs.CompleteUserDTO;
import com.tasklist.Domain.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.UUID;

@Service
public class JWTService {
    private static final String SECRET_KEY = "secret";

    private static final String ISSUER = "tasklist-api";

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(3600))
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId().toString())
                    .withClaim("username", user.getUsername())
                    .withClaim("isEnabled", user.isEnabled())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new JWTCreationException("Error on token generation.", exception);
        }
    }

    public CompleteUserDTO verify(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            DecodedJWT jwt = verifier.verify(token);
            CompleteUserDTO user =  new CompleteUserDTO(
                    jwt.getClaim("id").as(UUID.class),
                    jwt.getClaim("username").asString()
            );
            return user;

        } catch (JWTVerificationException exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error on handling login");
        }



    }
}
