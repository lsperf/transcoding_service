package com.test.transcoder.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

/**
 * Created by ls on 8/22/16.
 */
public class ClientAuthProvider extends DaoAuthenticationProvider{

	public boolean supports(Class<?> authentication) {
		return (ClientAuthToken.class.equals(authentication));
	}
}
