package org.example.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

	// todo: before deploying anything, regenerate this and find solution to store secrets safely
	private static final String SECRET_KEY = "0f6693ea222353fce82c6dcb453c59e829a54498020c74ce912b9caa9933908dfa89e8eecae25c6af87f650e0f331f488294e59ac9d32a03d42b9c5b5edb7bf95c7df28592d2856c8d4c2f28dd5a15d713f0d0f920664d20eddbd0d5aee348967e6f721ca0c2953617b514ef4f9ab55b2b09ea92f7e3037d20222e64c0f59e22a42984be30446754e57c6de9bdb52470cb24300a5fa8f9e8c27162c08c434afbd3a78055b248b1ef01aaab9150be7176421fa31741c47aefcacb07cf493bd3df442b0ab1dfd590a1f5395f257b8c2efb982809fdc009035fbcc4570d2c651e265fd727f8b71de1cacdea33882358b06f9a52b978395ac98c194b5a3b38284855";


	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Claims extractAllClaims(String token) {
		return Jwts
				.parser()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
