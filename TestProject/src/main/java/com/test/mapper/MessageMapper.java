package com.test.mapper;

import java.util.ArrayList;

import com.test.dto.MessageDto;
import com.test.dto.UserDto;

public interface MessageMapper {
	
	public ArrayList<UserDto> selectDepth2(UserDto userDto);
	public ArrayList<UserDto> selectDepth3(UserDto userDto);
	public UserDto selectIdName(UserDto userDto);
	public ArrayList<MessageDto> selectReceiveMsg(MessageDto messageDto);

	

}
