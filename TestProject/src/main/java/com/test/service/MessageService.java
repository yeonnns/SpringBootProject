package com.test.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dto.ChatDto;
import com.test.dto.ChatRoomDto;
import com.test.dto.MessageDto;
import com.test.dto.UserDto;
import com.test.entity.ChatEntity;
import com.test.entity.ChatRoomEntity;
import com.test.entity.MessageEntity;
import com.test.mapper.MessageMapper;
import com.test.repository.ChatRepository;
import com.test.repository.ChatRoomRepository;
import com.test.repository.MessageRepository;
import com.test.utill.DateContainer;

@Service
@Transactional
public class MessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
	
	@Autowired
	MessageMapper messageMapper;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	ChatRepository chatRepository;
	
	@Autowired
	ChatRoomRepository chatRoomRepository;
	
	public ArrayList<UserDto> selectDepth2(UserDto userDto) {
		return messageMapper.selectDepth2(userDto);
	}
	public ArrayList<UserDto> selectDepth3(UserDto userDto) {
		return messageMapper.selectDepth3(userDto);
	}
	public UserDto selectIdName(UserDto userDto) {
		return messageMapper.selectIdName(userDto);
	}
	public ArrayList<MessageDto> selectReceiveMsg(MessageDto messageDto) {
		return messageMapper.selectReceiveMsg(messageDto);
	}
	// insert
	public int insertMessage(MessageDto mDto, String[] recList) {
		MessageEntity result = new MessageEntity();
		for(String rec : recList) {
			mDto.setReceiveId(rec);
			mDto.setCreateDate(DateContainer.getNowDateTimeSecond());
			result = messageRepository.save(mDto.toEntity());
		} 
		return result.getNo();		
	}
	public int updateMsgRead(MessageDto mDto) {
		return messageRepository.updateMsgRead(mDto.getNoList());
	}
	public int updateMsgDel(MessageDto mDto) {
		return messageRepository.updateMsgDel(mDto.getNoList());
	}
}
