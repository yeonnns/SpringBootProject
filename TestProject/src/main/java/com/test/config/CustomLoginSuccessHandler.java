package com.test.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import com.test.service.UserService;


@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private final String DEAULT_LOGIN_SUCCESS_URL = "/";

	@Autowired
	private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		if(request.getParameter("remember-id")==null) {
			Cookie cookie = new Cookie("rememberId", null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}else {
			if(request.getParameter("remember-id").equals("on")) {
				Cookie cookie = new Cookie("rememberId", request.getParameter("username"));
				cookie.setMaxAge(60 * 60 * 24 * 30);
				response.addCookie(cookie);
			}
		}
		redirectStrategy.sendRedirect(request, response, DEAULT_LOGIN_SUCCESS_URL);
		
		
	}
	
	
}
