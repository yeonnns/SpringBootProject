package com.test.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.dto.ChatDto;
import com.test.dto.ChatRoomDto;
import com.test.dto.MessageDto;
import com.test.service.MessageService;
import com.test.utill.DateContainer;

import javassist.bytecode.stackmap.TypeData.ClassName;

@Component
@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	MessageService messageService;
	
	private static final Logger logger = LoggerFactory.getLogger(ClassName.class); 

	@RequestMapping("/sendmsg")
	public ModelAndView sendmsg(ModelAndView mv, MessageDto messageDto, @AuthenticationPrincipal UserDetails user) {
		
		String userName = user.getUsername();
		messageDto.setUserName(userName);
		ArrayList<MessageDto> receiveList = messageService.selectReceiveMsg(messageDto);
		
		mv.addObject("receiveList", receiveList);
		mv.setViewName("/message/sendMessage");
		return mv;
	}
	
}
