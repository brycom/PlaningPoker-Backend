package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.User;

@Service
public class TokenService {

    private JwtEncoder jwtEncoder;
    @SuppressWarnings("unused")
    private JwtDecoder jwtDecoder;
    @Autowired
    UserService userService;

    public TokenService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    public String jwtGenerator(Authentication auth) {

        Instant now = Instant.now();
        Instant expirationTime = now.plus(1, ChronoUnit.HOURS);

        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expirationTime)
                .subject(auth.getName())
                .claim("roles", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String getUserFromToken(Jwt jwt) {
        if (jwt != null) {
            String username = jwt.getSubject();
            User user = userService.getUserByUsername(username);
            return user.getUserId();

        } else
            throw new IllegalArgumentException("Token is missing");

    }
}
