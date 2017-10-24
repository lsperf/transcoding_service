package com.test.common.security;

import com.test.common.data.entity.ClientEntity;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by ls on 8/22/16.
 */
public class SecureUserFactory {


	public static SecureUser create(ClientEntity clientEntity) {
		return new SecureUser(
			clientEntity.getId(),
			clientEntity.getUsername(),
			clientEntity.getPassword(),
			AuthorityUtils.commaSeparatedStringToAuthorityList(clientEntity.getAuthorities())
		);
	}
	
}
