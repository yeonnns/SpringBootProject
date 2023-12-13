package com.test.dto;

import lombok.Data;

@Data
public class MailSendDto {
	private String sendAddress;
	private String sendPw;
	private String receiveAddress; // 받는 사람
	private String depth3;  // 발신자 이름
	private String depth2;  
	private String depth1; 
	
	private String host;
	private int port;
	private String title;
	private String contents;
	private String receiveId;
	private String result;
	
	// 에러 관련
	/*
	private int errorCode;
	private String errorType;
	private String errorTime;
	private String exception;
	private StackTraceElement[] stackTrace;
	private String errorPath;
	private String clientIp;
	 */
	
}
