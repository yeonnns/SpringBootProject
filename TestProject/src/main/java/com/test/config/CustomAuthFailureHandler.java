package com.test.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
		
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		 String errorMessage;
		if (exception instanceof AuthenticationServiceException) {
			errorMessage = "존재하지 않는 사용자입니다.";
		
		} else if(exception instanceof BadCredentialsException) {
			errorMessage = "비밀번호가 틀립니다.";
			
		} else if(exception instanceof UsernameNotFoundException ) {
			errorMessage = "아이디가 없을 경우";
			
		} 
		else if(exception instanceof LockedException) {
			errorMessage =  "잠긴 계정입니다.";
			
		} else if(exception instanceof DisabledException) {
			errorMessage = "비활성화된 계정입니다.";
			
		}  else {
			errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다 관리자에게 문의하세요.";
		}
		 setDefaultFailureUrl("/login?error=true&exception="+errorMessage);
		 logger.info("로그인 실패 이유  : "+errorMessage);
		 super.onAuthenticationFailure(request, response, exception);
	
	}
}

