package com.example.back.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Represents the utility class for JWT (JSON Web Token) operations.
 */
@Component
public class JwtUtil {

    /**
     * The Base64 encoded key used for signing the JWT.
     */
    private final String ENCODED_KEY = "Y2hpYXIgY3JlZGVhaSBjYSBlIGNldmEgdmFsb3Jvcz8=";
    
    /**
     * The secret key used for signing the JWT.
     */
    private final String SECRET_KEY = new String(Base64.getDecoder().decode(ENCODED_KEY));


    /**
     * Extracts the username from the JWT token.
     *
     * @param token the JWT token.
     * @return the extracted username.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param token the JWT token.
     * @return the expiration date.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from the JWT token using the given claims resolver.
     *
     * @param token          the JWT token.
     * @param claimsResolver the claims resolver function.
     * @param <T>            the type of the claim.
     * @return the extracted claim.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from the JWT token.
     *
     * @param token the JWT token.
     * @return all claims.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * Checks if the JWT token is expired.
     *
     * @param token the JWT token.
     * @return true if the token is expired, false otherwise.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Generates a JWT token for the specified email.
     *
     * @param username the email for whom the token is generated.
     * @return the generated JWT token.
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /**
     * Creates a JWT token with the specified claims and subject.
     *
     * @param claims  the claims to be included in the token.
     * @param subject the subject of the token.
     * @return the created JWT token.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /**
     * Validates the JWT token.
     *
     * @param token    the JWT token to be validated.
     * @param username the username against which the token is validated.
     * @return true if the token is valid, false otherwise.
     */
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
