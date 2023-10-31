package com.test.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {
	private List<String> nameList; // 알람에서 이름 id로 변환할때 사용
	private List<String> idList; // 변환된 id 
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
