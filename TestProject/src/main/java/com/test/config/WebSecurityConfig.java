package com.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.test.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity  // 스프링 시큐리티 필터가 스프링 필터 체인에 등록
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	/* 로그인 실패 핸들러 의존성 주입 */    
	private final AuthenticationFailureHandler customFailureHandler;

	
	
	@Autowired
	private CustomAuthenticationProvider authProvider;
	 
	@Autowired
	UserService userService;
	
	@Value("${security.invalidate-session}")
	private boolean invalidateSession;

	
	// 회원가입 시 비밀번호 암호화에 사용할 Encoder 빈 등록 
	  @Bean    
	  public BCryptPasswordEncoder encoder() {
		  return new BCryptPasswordEncoder();
		  }    
	  
	  @Bean
	  public AuthenticationProvider authenticationProvider() {
	  	return new CustomAuthenticationProvider();
	  }

 
  @Override    
  public void configure(WebSecurity web) {
	  web                
	  .ignoring().antMatchers( "/css/**", "/js/**", "/img/**", "/upload/**", "/resources/**");    
	  }        
  
  
  
  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userService).passwordEncoder(encoder());
     auth.authenticationProvider(authProvider);
  }
  
  
  @Override    
  protected void configure(HttpSecurity http) throws Exception {

	  http
        .csrf().disable() // Cross site Request forgery 비정상적사용자가 위조요청 보내는것 막음
	    .formLogin().disable()
	  	.headers().frameOptions().disable();
	  
	  http.rememberMe()
		  .key("uniqueAndSecret")
		  .rememberMeParameter("remember-me")
		  .tokenValiditySeconds(60 * 60 * 12)
		  .userDetailsService(userService);
	  
	 
	  http                
		  .authorizeRequests()                
		  .antMatchers("/join"
				  ,"/loginProc"
				  ,"/login"
				  ,"/joinProc"
				  ,"/test"
				  ).permitAll()       
		  .antMatchers("/admin").hasAnyAuthority("ROLE_ADMIN")
		  .antMatchers("/user").hasAnyAuthority("ROLE_USER")
		  .anyRequest().authenticated(); 
	  http
	  	  .formLogin()               
		  .loginPage("/login")               
		  .loginProcessingUrl("/loginProc")  
		  .defaultSuccessUrl("/")
		  .successHandler(authenticationSuccessHandler())
		  .failureHandler(customFailureHandler)
	  .and()
			.logout()
			.logoutSuccessUrl("/login")
		    .invalidateHttpSession(invalidateSession)
			.permitAll()
		.and()
			.sessionManagement()
	         // 중복 로그인을 막음.
	        .maximumSessions(1)
	         // 세션만료시 이동할 url
	        .expiredUrl("/login")
	         // false이면 중복로그인시 기존세션 제거, true이면 신규 로그인을 막음.
	        .maxSessionsPreventsLogin(false);
	  
	  }
 
  @Bean 
  public AuthenticationSuccessHandler authenticationSuccessHandler() { 
  	return new CustomLoginSuccessHandler(); 
  }
  
}
