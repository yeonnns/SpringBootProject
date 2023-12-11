package com.test.controller;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.service.WeatherService;

@Controller
@RequestMapping("/weather")
public class weatherController {
	
	@Autowired
	WeatherService weatherService;
	
	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
	
	@RequestMapping(value="/fineDust")
	public ModelAndView fineDust(ModelAndView mv) throws Exception {
		weatherService.fineDustInsert();
		mv.setViewName("/weather/fineDust");
		return mv;
	}
		

}
