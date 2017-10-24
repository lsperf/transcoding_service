package com.test.transcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.test.transcoder.security.ClientAuthToken;
import com.test.transcoder.security.service.ClientDetailsService;
import com.test.common.data.entity.ClientEntity;
import com.test.common.data.service.ClientDataService;
import com.test.common.security.TokenUtils;
import com.test.common.security.UserType;
import com.test.common.utils.StringConstants;
import com.test.transcoder.beans.LoginBean;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	protected AuthenticationManager authenticationManager;

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Autowired
	private ClientDataService clientDataService;

	@Autowired
	protected TokenUtils tokenUtils;

	@Autowired
	protected PasswordEncoder passwordEncoder;

	@Value("${security.token.header}")
	private String tokenHeader;
	@Value("${security.token.secret}")
	private String secret;
	@Value("${security.token.expiration}")
	private long expiration;

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(Model model) {
		LoginBean loginBean = new LoginBean();
		model.addAttribute("loginBean", loginBean);
		return "login";
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String loginPost(@ModelAttribute LoginBean loginBean, Model model, HttpServletResponse response) {

		try{
			ClientEntity clientEntity = clientDataService.findByUsername(loginBean.getUsername());
			if(clientEntity == null){
				login(model);
			}

			if(!passwordEncoder.matches(loginBean.getPassword(), clientEntity.getPassword())){
				login(model);
			}

			String username = loginBean.getUsername();
			String password = loginBean.getPassword();

			authenticate(authenticationManager, username, password);

			UserDetails userDetails = this.clientDetailsService.loadUserByUsername(loginBean.getUsername());
			String token = this.tokenUtils.generateToken(userDetails, UserType.CLIENT, secret, expiration);
			response.addCookie(new Cookie(StringConstants.CLIENT_COOKIE_NAME, token));

			return "redirect:/upload";
		}
		catch (Exception exp){
			return login(model);
		}
	}


	@RequestMapping(value="/log-out", method = RequestMethod.GET)
	public String logout(Model model, HttpServletResponse response) {
		Cookie cookie = new Cookie(StringConstants.CLIENT_COOKIE_NAME, "");
		cookie.setMaxAge(0);
		cookie.setValue("");
		response.addCookie(cookie);
		return login(model);
	}

	private Authentication authenticate(AuthenticationManager manager , String username, String password){
		Authentication authentication = manager.authenticate(
				new ClientAuthToken(
						username, password
				)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return authentication;
	}


}
