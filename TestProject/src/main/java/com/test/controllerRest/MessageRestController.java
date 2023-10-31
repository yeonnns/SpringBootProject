package com.test.controllerRest;

import java.util.ArrayList;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.dto.MessageDto;
import com.test.dto.UserDto;
import com.test.service.MessageService;

@Component
@Controller
@RequestMapping("/message/api")
public class MessageRestController{
	
	
	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = "/selectDepth2")
	public ModelAndView selectDepth2(UserDto userDto) {
		ModelAndView mv = new ModelAndView("jsonView");
		ArrayList<UserDto> result = messageService.selectDepth2(userDto);
		mv.addObject("result", result);
		return mv;
	}
	
	@RequestMapping(value = "/selectDepth3")
	public ModelAndView selectDepth3(UserDto userDto) {
		ModelAndView mv = new ModelAndView("jsonView");
		ArrayList<UserDto> result = messageService.selectDepth3(userDto);
		mv.addObject("result", result);
		return mv;
	}
	
	@RequestMapping(value = "/selectIdName")
	public ModelAndView selectIdName(UserDto userDto) {
		ModelAndView mv = new ModelAndView("jsonView");
		UserDto result = messageService.selectIdName(userDto);
		mv.addObject("result", result);
		return mv;
	}
	
	@RequestMapping(value="/updateMsgRead")
	public ModelAndView updateMsgRead(MessageDto messageDto) {
		ModelAndView mv = new ModelAndView("jsonView");
		int result = messageService.updateMsgRead(messageDto);
		mv.addObject("result", result);
		return mv;
	}
	
	@RequestMapping(value="/updateMsgDel")
	public ModelAndView updateMsgDel(MessageDto messageDto) {
		ModelAndView mv = new ModelAndView("jsonView");
		int result = messageService.updateMsgDel(messageDto);
		mv.addObject("result", result);
		return mv;
	}
	
}
