package com.test.mapper;

import java.util.ArrayList;

import com.test.dto.UserDto;

public interface UserMapper {

	public UserDto UserInfo(String username);
	public int userInsert(UserDto userDto);
	public ArrayList<UserDto> latestJoin();

}
