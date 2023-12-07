package com.test.controllerRest;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.dto.MailSendDto;
import com.test.service.MailService;
import com.test.service.UserService;

@Component
@Controller
@RequestMapping("/api")
public class MainRestController{
	
	
	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	
	@RequestMapping(value = "/daySalesChart")
	public ModelAndView daySalesChart() {
		ModelAndView mv = new ModelAndView("jsonView");
		int[] daySales = userService.daySales();
		mv.addObject("result", daySales);
		
		return mv;
	}
	
	@RequestMapping(value = "/monthSalesChart")
	public ModelAndView monthSalesChart() {
		ModelAndView mv = new ModelAndView("jsonView");
		int[] monthSales = userService.monthSales();
		mv.addObject("result", monthSales);
		return mv;
	}
}
