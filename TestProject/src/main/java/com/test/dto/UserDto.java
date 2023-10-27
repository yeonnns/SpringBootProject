package com.test.dto;

import lombok.Data;

@Data
public class UserDto {
	private String emailId;
	private String username;
	private String password;
	
	private String name;
	private String depth1;
	private String depth2;
	private String position;
	private String depth3;
	private String userNo;
	private String createDate;
	//private String monthDate; // 1~31일 group
	
	private String role;
	private String delYN;
	private String deleteDate;
	
	private String changedPw;
	private int tryCount;
}
