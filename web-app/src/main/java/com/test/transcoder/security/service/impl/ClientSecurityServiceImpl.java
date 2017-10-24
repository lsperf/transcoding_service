package com.test.transcoder.security.service.impl;

import com.test.transcoder.security.service.ClientSecurityService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by ls on 8/22/16.
 */
@Service
public class ClientSecurityServiceImpl implements ClientSecurityService {

	@Override
	public Boolean hasProtectedAccess() {
		return (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")));
	}
}
