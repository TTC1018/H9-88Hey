package softeer.h9.hey.auth.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import softeer.h9.hey.auth.exception.InvalidTokenException;

@Component
public class JwtTokenProvider {
	private final Key secretKey;
	private final long accessTokenExpiredTimeMs;
	private final long refreshTokenExpiredTimeMs;
	private final JwtParser jwtParser;

	public JwtTokenProvider(
		@Value("${auth.jwt.token.secret-key}") String secretKey,
		@Value("${auth.jwt.token.access.expire-time}") long accessTokenExpiredTimeMs,
		@Value("${auth.jwt.token.refresh.expire-time}") long refreshTokenExpiredTimeMs
	) {
		this.secretKey = getKey(secretKey);
		this.accessTokenExpiredTimeMs = accessTokenExpiredTimeMs;
		this.refreshTokenExpiredTimeMs = refreshTokenExpiredTimeMs;
		this.jwtParser = getJwtParser(this.secretKey);
	}

	private Key getKey(String key) {
		byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private JwtParser getJwtParser(Key secretKey) {
		return Jwts.parserBuilder()
			.setSigningKey(secretKey)
			.build();
	}

	public String generateAccessToken(int subject, Map<String, Object> claims) {
		return Jwts.builder()
			.setSubject(String.valueOf(subject))
			.addClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiredTimeMs))
			.signWith(secretKey)
			.compact();
	}

	public String generateRefreshToken(int subject, Map<String, Object> claims) {
		return Jwts.builder()
			.setSubject(String.valueOf(subject))
			.addClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiredTimeMs))
			.signWith(secretKey)
			.compact();
	}

	public Map<String, Object> getClaimsFromToken(String jwt) {
		try {
			return jwtParser
				.parseClaimsJws(jwt)
				.getBody();
		} catch (JwtException e) {
			throw new InvalidTokenException();
		}
	}
}
