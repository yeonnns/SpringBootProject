package com.test.controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.config.PrincipalDetails;
import com.test.dto.MailSendDto;
import com.test.service.MailService;


@Controller
public class CustomErrorController implements ErrorController{

	
	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);

	@Autowired
	private MailService mailService;
	
	private static final String ERROR_PATH = "/error";

	// 상속받은 method 재정의 -> 에러 발생 시 호출할 사용자 경로 지정
	@Override
	public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model, MailSendDto mDto) {
    	//유저 정보
    	String depth1 ="";
    	String depth2 ="";
    	String depth3 ="";
    	
    	Object principal = SecurityContextHolder.getContext().getAuthentication();
		if(principal!=null) {
			Object userDetail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(!userDetail.equals("anonymousUser")) {
				PrincipalDetails user = (PrincipalDetails)userDetail;
				depth1 = user.getDepth1();
				depth2 = user.getDepth2();
				depth3 = user.getDepth3();
			}
		}
		
    	//exception 이름
    	String exception = "";
    	//에러 메세지
    	String message = "";
    	//에러 스택
    	StackTraceElement[] stackTrace = null;
    	//에러 경로
    	String errorPath = "";
    	
    	try {
    		// /error 경로로 들어온 에러의 status를 불러온다 (ex :  404, 500, 403)
    		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    		//e.toString() : 에러의 Exception 내용과 원인을 출력
            HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
            Object path = request.getAttribute("javax.servlet.error.request_uri"); //문제를 일으킨 리소스의 URI
            errorPath = path.toString();
            
            
            Object exceptionObj = request.getAttribute("javax.servlet.error.exception"); //실제 예외가 없어지면 버릴 수 있는 객체
            if(exceptionObj != null) {
            	Throwable e = ((Exception) exceptionObj).getCause();
            	exception = e.getClass().getName();
            	message = e.getMessage();
            	stackTrace = e.getStackTrace();
            }
            
            //에러 코드
            String errorCode = status.toString();
            //에러 타입
            String errorType =  httpStatus.getReasonPhrase();
            
            SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
            //에러 시간
            Date time = new Date();
            String errorTime = format.format(time);
            
            
            //ip 주소 식별 -> http프록시나 로드밸런서를 통해 웹서버 접속하는 클라이언트
    		String clientIp = request.getHeader("X-Forwarded-For");
     
            if(clientIp == null) {
            	clientIp = request.getHeader("Proxy-Client-IP");
            }
            if(clientIp == null) {
            	clientIp = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
            }
            if(clientIp == null) {
            	clientIp = request.getHeader("HTTP_CLIENT_IP");
            }
            if(clientIp == null) {
            	clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if(clientIp == null) {
            	clientIp = request.getRemoteAddr();
            }

            if(errorPath.contains("/js/") || errorPath.contains("/css/") || errorPath.contains("/img/") || errorPath.contains("/scss/") || errorPath.contains("/vender/") || errorCode.equals("404")) {
            	
            }else if(errorCode.equals("403") && errorType.equals("Forbidden")) {
            	model.addAttribute("type", errorType);
            }else {
            	try {
            		//mailService.sendErrorMail(errorCode, errorType, errorTime, exception, message, stackTrace, errorPath, clientIp, depth1, depth2, depth3);
    			} catch (Exception e) {
    				logger.info("---------- Error 이메일 전송 Exception 발생 ----------");
    				logger.info(e.toString());
    			}
            }
            model.addAttribute("code", errorCode);
		} catch (Exception e) {
			logger.info("---------- CustomErrorController Exception 발생 ----------");
			logger.info(e.toString());
		}
        return "/common/error";
    }
}
