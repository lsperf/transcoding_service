package com.test.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by ls on 8/22/16.
 */
@Component
public class TokenUtils {

	private static final String UTYPE = "u-type";

	public static String getUsernameFromToken(String token, String secret) {
		String username;
		try {
			username = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public static UserType getUserTypeFromToken(String token, String secret) {
		String type;
		UserType t;
		try {
			type = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token).getBody().get(UTYPE, String.class);
			t = UserType.getByLabel(type);

		} catch (Exception e) {
			t = null;
		}
		return t;
	}

	private static Date getExpirationDate(String token, String secret) {
		Date expiration;
		try {
			expiration = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody()
				.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	public static String generateToken(UserDetails userDetails, UserType userType, String secret, long expiration) {
		Claims customClaims = Jwts.claims();
		customClaims.put(UTYPE, userType);
		return Jwts.builder().setClaims(customClaims)
			.setSubject(userDetails.getUsername())
			.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
			.signWith(SignatureAlgorithm.HS512, secret)
			.compact();
	}

	public static boolean validateToken(String token, UserDetails userDetails, String secret) {
		final String username = getUsernameFromToken(token, secret);
		final Date expiration = getExpirationDate(token, secret);
		return username.equals(userDetails.getUsername()) &&
			expiration.after(new Date(System.currentTimeMillis()));
	}
}
