package com.test.controller;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.dto.SalesDto;
import com.test.dto.UserDto;
import com.test.service.CodeService;
import com.test.service.UserService;

@Component
@Controller
public class MainController{
	
	
	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CodeService codeService;
	
	@RequestMapping(value = "/" )
	public ModelAndView main(Authentication auth) {
		logger.info("#########main");
		ModelAndView mv = new ModelAndView();
		mv.addObject("auth", auth);
		
	
		ArrayList<UserDto> latestJoin = userService.latestJoin();
		mv.addObject("latestJoin", latestJoin);		
		
		mv.setViewName("/main");
		return mv;
	}
	
	
	@RequestMapping(value = "/join")
	public ModelAndView join(HttpSession session) {
		logger.info("#########join");
		ModelAndView mv = new ModelAndView();
		
		
		mv.setViewName("/user/join");
		return mv;
	}
	@RequestMapping(value = "/joinProc")
	public ModelAndView joinProc(UserDto uDto) {
		logger.info("#########/joinProc");
		ModelAndView mv = new ModelAndView();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("전" + uDto.getPassword());
		uDto.setPassword(passwordEncoder.encode(uDto.getPassword()));
		System.out.println("후" + uDto.getPassword());
		int cnt = userService.userInsert(uDto);
		
		if(cnt == 1) {
		mv.setViewName("/user/login");
		}else {
		mv.setViewName("/user/join");
		}
		return mv;
	}
	
	@RequestMapping(value = "/admin")
	public ModelAndView admin() {
		logger.info("#########admin");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin");
		return mv;
	}
	@RequestMapping(value = "/user")
	public ModelAndView user() {
		logger.info("#########user");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user");
		return mv;
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, HttpSession session, HttpServletResponse response, @CookieValue(value = "rememberId", required = false) Cookie cookie) {
		logger.info("#########login");
		ModelAndView mv = new ModelAndView();
	
		  if(cookie !=null) {
			  mv.addObject("rememberId",cookie.getValue()); 
			  }
		
		mv.setViewName("/user/login");
		return mv;
	}
}
