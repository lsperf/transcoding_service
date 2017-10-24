package com.test.transcoder.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by ls on 8/22/16.
 */
public class ClientAuthToken extends UsernamePasswordAuthenticationToken {

	public ClientAuthToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public ClientAuthToken(Object principal, Object credentials,
						   Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

}
