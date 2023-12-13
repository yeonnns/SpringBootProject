package com.test.service;

import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dto.MailSendDto;
import com.test.mapper.UserMapper;

@Service
public class MailService {

	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
	
	
	@Value("${mail.error.address}")
	private String errorAddress;
	
	@Autowired
	UserMapper userMapper;

	// 에러메일 보내기 전 데이터 셋팅
	public void sendErrorMail(String errorCode, String errorType, String errorTime, String exception, String message, StackTraceElement[] stackTrace, String errorPath, String clientIp, String depth1, String depth2, String depth3) throws Exception {
		if(!errorAddress.isEmpty()) {
			
			String str = "";
			if(stackTrace!=null) {
				for(StackTraceElement s : stackTrace) {
					str += s.toString()+"<br>";
				}
			}
			
			MailSendDto mDto = new MailSendDto();
			mDto.setPort(465);
			mDto.setSendAddress("emailAddress");
			mDto.setSendPw("emailPw");
			mDto.setReceiveAddress("emailReceive");
			
			mDto.setTitle("[testProject] Error("+errorCode+")");
			
			if(errorCode.equals("500")) {
				errorCode += "  [서버 내부 에러 - 요청 사항 수행 불가]";
			}else if(errorCode.equals("404")) {
				errorCode += "  [클라이언트 에러 - 요청 문서 찾을 수 없음]";
			}else if(errorCode.equals("400")) {
				errorCode += "  [클라이언트 문법 에러]";
			}else if(errorCode.equals("415")) {
				errorCode += "  [지원되지 않은 형식 요청 - 승인 거부]";
			}else if(errorCode.equals("505")) {
				errorCode += "  [HTTP Version Not Supported]";
			}else if(errorCode.equals("405")) {
				errorCode += "  [메소드를 수행하기 위한 자원 허용 안됨]";
			}
			
			mDto.setContents("<p style='font-size:14px; font-family: 맑은 고딕'><b>testProject - 에러 발송 메일</b><br>"
									+"-------------------------------------------------<br><br>"
									+"<b>발생 시간 : </b>" + errorTime + "<br><br>"
									+"<b>클라이언트 IP : </b>" + clientIp  + "<br><br>"
									+"<b>사용자 정보 : </b>" + depth1  + " / " + depth2 + " / "+ depth3 + "<br><br>"
									+"<b>에러 코드 : </b>" + errorCode + "<br><br>"
									+"<b>에러 타입 : </b>" + errorType + "<br><br>"
									+"<b>에러 명 : </b>" + exception + "<br><br>"
									+"<b>에러 경로 : </b>" + errorPath + "<br><br>"
									+"<b>에러 내용 : </b>" + message + "<br><br>"
									+"<b>스택 : </b><br>" + str + "</p>");
			mailConnect(mDto);
		}
	}
	
	// 메일 보내기 전 데이터 셋팅
	public boolean sendMail(MailSendDto mDto) {
		boolean result = false;
		mDto.setPort(165);
		mDto.setSendAddress("emailAddress");
		mDto.setSendPw("emailPw");
		mDto.setReceiveAddress("emailReceive");
		result = mailConnect(mDto);
		return result;
	}
	
	@Transactional
	public boolean mailConnect(MailSendDto mDto) {
		String sendAddress = mDto.getSendAddress();
		String title = mDto.getTitle();
		String contents = mDto.getContents();
		String personName = mDto.getDepth3();
		String recipient = mDto.getReceiveAddress();
		String sendPw = mDto.getSendPw();
		
		try {
		String host = "사용할 host"; 
		final String userName = sendAddress;// 보내는사람 이메일
		final String password = sendPw;		// 보내는 사람 메일 패스워드
		int port = 465;
		
		Properties props = System.getProperties(); // 정보를 담기 위한 객체 생성 // SMTP 서버 정보 설정 
        props.put("mail.smtp.host", host); 
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.ssl.enable", "true"); 
        props.put("mail.smtp.ssl.trust", host);	
        
        //Session 생성
        Session session = Session.getInstance(props, new javax.mail.Authenticator() { 
            String un = userName; 
            String pw = password; 
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() { 
                    return new javax.mail.PasswordAuthentication(un, pw); 
            } 
        });
        session.setDebug(true); //for debug 
        
        // mimeMessage 생성
        //MIME : Multipurpose Internet Mail Extensions 인코딩방식
        Message mimeMessage = new MimeMessage(session); 
        mimeMessage.setFrom(new InternetAddress(sendAddress, personName, "UTF-8"));
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
        mimeMessage.setSubject(title);
        
        // 각 첨부파일 또는 대체 메시지 본문의 MimeBodyPart 객체
        MimeBodyPart mbp = new MimeBodyPart();
        mbp.setContent(contents, "text/html;charset=utf-8");// 내용
        
        //message 객체에 본문을 넣기 위하여 Multipart 객체 생성
    	Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbp);
        
		MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		CommandMap.setDefaultCommandMap(mc);
		mimeMessage.setContent(mp, "text/html;charset=utf-8");
		
		Transport.send(mimeMessage);
		logger.info("========================== "+title+" 이메일 발송 완료 ==========================");
		return true;
		} catch (Exception e) {
			logger.info("========================!!! "+title+" 이메일 발송 오류 !!!=======================");
			logger.info(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}
