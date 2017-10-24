package com.test.transcoder.config;

import com.test.transcoder.security.AuthTokenFilter;
import com.test.transcoder.security.EntryPointUnauthorizedHandler;
import com.test.transcoder.security.service.ClientSecurityService;
import com.test.transcoder.security.ClientAuthProvider;
import com.test.transcoder.security.service.ClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by ls on 12/11/16.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private EntryPointUnauthorizedHandler unauthorizedHandler;

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Autowired
	private ClientSecurityService clientSecurityService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public ClientDetailsService clientDetailsService() {
		return clientDetailsService;
	}

	@Bean
	public ClientSecurityService clientSecurityService() {
		return clientSecurityService;
	}


	@Autowired
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
			.authenticationProvider(clientAuthProvider());

	}

	@Bean
	public ClientAuthProvider clientAuthProvider() {
		ClientAuthProvider clientAuthProvider = new ClientAuthProvider();
		clientAuthProvider.setUserDetailsService(clientDetailsService());
		clientAuthProvider.setPasswordEncoder(passwordEncoder());
		return clientAuthProvider;
	}

	@Bean
	public AuthTokenFilter authenticationTokenFilterBean() throws Exception {
		AuthTokenFilter authenticationTokenFilter = new AuthTokenFilter();
		authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
		return authenticationTokenFilter;
	}


	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.antMatcher("/**")
			.csrf()
			.disable()
			.exceptionHandling()
			.authenticationEntryPoint(this.unauthorizedHandler)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			.antMatchers("/login/**").permitAll()
			.antMatchers("/log-out/**").permitAll()
			.antMatchers("/login/verification/**").permitAll()
			.antMatchers("/resources/**").permitAll()
			.anyRequest().authenticated();
//			.and()
//			.formLogin();
//			.failureUrl("/login?error");

		httpSecurity
			.addFilterBefore(authenticationTokenFilterBean(), AuthTokenFilter.class);

	}


}
