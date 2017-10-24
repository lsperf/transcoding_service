package com.test.transcoder.security;

import com.test.transcoder.security.service.ClientDetailsService;
import com.test.common.security.TokenUtils;
import com.test.common.security.UserType;
import com.test.common.utils.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthTokenFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Autowired
	private TokenUtils tokenUtils;

	@Value("${security.token.header}")
	private String tokenHeader;
	@Value("${security.token.secret}")
	private String secret;
	@Value("${security.token.expiration}")
	private long expiration;


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		doInnerFilterBO(httpRequest);
		chain.doFilter(request, response);
	}

	private void doInnerFilterBO(HttpServletRequest request) throws IOException, ServletException {
		String authToken = getTokenFromCookie(request);
		try {
			if(authToken != null){
				UserType userType = this.tokenUtils.getUserTypeFromToken(authToken, secret);
				if(userType == UserType.CLIENT){
					String username = this.tokenUtils.getUsernameFromToken(authToken, secret);
					UserDetails userDetails = clientDetailsService.loadUserByUsername(username);
					if (this.tokenUtils.validateToken(authToken, userDetails, secret)) {
						ClientAuthToken authentication = new ClientAuthToken(userDetails, null, userDetails.getAuthorities());
						authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
				}
			}
		}
		catch (Exception exp){
			// @TODO log this
		}

	}

	private String getTokenFromCookie(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies == null)
			return null;

		for(int i=0;i < cookies.length; i++){
			if(StringConstants.CLIENT_COOKIE_NAME.equals(cookies[i].getName())){
				return cookies[i].getValue();
			}
		}

		return null;
	}
}
