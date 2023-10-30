package com.test.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.test.dto.SalaryDto;
import com.test.service.FileService;
import com.test.utill.DateContainer;
import com.test.utill.FileUtil;

@Component
@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);

	@Autowired
	FileService fileService;

	@RequestMapping(value = "/salaryList")
	public ModelAndView salaryList(SalaryDto salDto) throws Exception {
		logger.info("salaryList");
		ModelAndView mv = new ModelAndView();
		String now = ""; 
		if(salDto.getNowMonth() != null) {
			 now = salDto.getNowMonth();
		} else {
			 now = DateContainer.getNowDate(); // 현재 날짜
		}
		salDto.setNowMonth(now);
		ArrayList<SalaryDto> salaryList = fileService.getSalaryList(salDto);
		
		mv.addObject("nowMonth", now.substring(0, 7));
		mv.addObject("salaryList", salaryList);
		mv.setViewName("/hire/salaryList");
		return mv;
	}
	
	@RequestMapping(value = "/salaryFileUpload")
	public ModelAndView salaryFileUpload(RedirectView rv, HttpServletRequest req, HttpServletResponse rep) throws Exception {
		ModelAndView mv = new ModelAndView();
		logger.info("salaryFileUpload");
		String name = "salaryFile";
		fileService.salaryFileUpload(FileUtil.salesFileUpload(req, rep, name));
		
		rv.setUrl("/encryTest");
		mv.setView(rv);
		
		return mv;
	}
	

	
}
