package com.mini.crate.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtUtils {

	@Value("${crate.app.jwtSecret}")
	private String jwtSecret;

	public DecodedJWT decodeJwtToken(String token) {
		Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
		JWTVerifier verifier = JWT.require(algorithm).build();
		return verifier.verify(token);
	}

	public String encodeToken(String username, Date expiration, String issuer, List<String> roles) {
		Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
		return JWT.create()
				.withSubject(username)
				.withExpiresAt(expiration)
				.withIssuer(issuer)
				.withClaim("roles", roles)
				.sign(algorithm);
	}

	public String encodeRefreshToken(String username, Date expiration, String issuer) {
		Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
		return JWT.create()
				.withSubject(username)
				.withExpiresAt(expiration)
				.withIssuer(issuer)
				.sign(algorithm);
	}


}
