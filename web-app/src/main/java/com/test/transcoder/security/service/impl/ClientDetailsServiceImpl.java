package com.test.transcoder.security.service.impl;

import com.test.transcoder.security.service.ClientDetailsService;
import com.test.common.data.entity.ClientEntity;
import com.test.common.data.service.ClientDataService;
import com.test.common.security.SecureUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by ls on 8/22/16.
 */
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	private ClientDataService clientDataService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ClientEntity user = this.clientDataService.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return SecureUserFactory.create(user);
		}
	}
}
