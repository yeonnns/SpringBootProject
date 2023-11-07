package com.test.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.test.dto.ChatDto;
import com.test.dto.MessageDto;
import com.test.service.MessageService;

@Repository
public class WebSocketHandler extends TextWebSocketHandler {
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();	
	
	@Autowired
	private MessageService messageService;
	
	private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
	
	// 웹소켓 클라이언트와 연결
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		String sendId = getMemberId(session);
		sessionList.add(session);
		if(sendId != null) {
			sessionList.add(session); // 로그인중 개별 유저 저장
			//logger.info("{} 연결됨", getMemberId(session));
		}
	}

	// 웹소켓 클라이언트가 텍스트 메시지를 전송할 때 호출
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("{}로 부터 {} 받음", getMemberId(session), message.getPayload());
		// 보내는 사람 session
		String sendId = getMemberId(session);
		//socket.send(receiveId + "&" + title + "&" + content + "&" + type);
		//socket.send(inRoomNo + "&" + content + "&" + second + "&" + type + "&" + time);
		String msg = message.getPayload();
		String[] strs = msg.split("&");
		MessageDto mdSelf = new MessageDto(); // 본인한테 쪽지
		MessageDto mdRec = new MessageDto(); // 받는이들한테 쪽지 
		ChatDto chat = new ChatDto(); // Chat
		
		
		switch (strs[3]) {
		case "chat" : 
			if(strs != null && strs.length == 5) {
				chat.setRoomNo(Integer.parseInt(strs[0]));
				chat.setSendId(sendId);
				chat.setContents(strs[1]);
				chat.setIsRead("N");

				
				//  socket.onmessage = function (e) {} 작용하기 위해서
				for(WebSocketSession sess : sessionList) {
					TextMessage tmpMsg;
					//tmpMsg = new TextMessage(text);
					String contents = "<li class='you'>"
							+ "<div class='entete'>"
							+ "<h3>" + strs[4] + "</h3>"
							+ "<h2>&nbsp;&nbsp;" + strs[2] + "&nbsp;</h2>"
							+ "<span class='status green'></span></div>"
							+ "<div class='triangle'></div>"
							+ "<div class='message'>"+strs[1]+"</div></li>";
					
					String text = "<div class='newMsg blinking'>chatting 도착</div>";
				
					JSONObject messageObj = new JSONObject();
					messageObj.put("type", strs[3]);
					messageObj.put("text", text);
					messageObj.put("contents", contents);
					
					if(getMemberId(sess).equals(sendId)) {
						try {
							//sess.sendMessage(tmpMsg);
							sess.sendMessage(new TextMessage(messageObj.toJSONString()));
						} catch (Exception e) {
							logger.info("[" + sess.getId() + "] 알림 전송 중 socket 연결 끊김");
						}
					}
					
				}
				messageService.insertChat(chat);
			}
			break;		
				
		case "message" : 
			if(strs != null && strs.length == 4) {
				mdSelf.setSendId(sendId); // 본인
				mdSelf.setType("sendSelf");
				mdSelf.setTitle(strs[1]);
				mdSelf.setContent(strs[2]);
				mdSelf.setIsRead("Y");
				
				String[] sendList = {sendId};
				String[] receiveList = strs[0].split(",");
				
				mdRec.setSendId(sendId);
				mdRec.setType("receive");
				mdRec.setTitle(strs[1]);
				mdRec.setContent(strs[2]);
				mdRec.setIsRead("N");
				
				//  socket.onmessage = function (e) {} 작용하기 위해서
				for(WebSocketSession sess : sessionList) {
					TextMessage tmpMsg;
					String text = "<div class='newMsg blinking'>message 도착</div>";
					//tmpMsg = new TextMessage(text);
					
					JSONObject messageObj = new JSONObject();
					messageObj.put("type", strs[3]);
					messageObj.put("text", text);
					
					for(String username : receiveList) {
						if(getMemberId(sess).equals(username)) {
							try {
								//sess.sendMessage(tmpMsg);
								sess.sendMessage(new TextMessage(messageObj.toJSONString()));
							} catch (Exception e) {
								logger.info("[" + sess.getId() + "] 알림 전송 중 socket 연결 끊김");
							}
						}
					}
				}
				int sendNo = messageService.insertMessage(mdSelf,sendList);
				mdRec.setSendNo(sendNo);
				logger.info("응답받은 sendNo {} ", sendNo);
				messageService.insertMessage(mdRec, receiveList);
			}
			break;			
			
		}
	}
	
	// 웹소켓 클라이언트와 연결해제
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		String senderId = getMemberId(session);
		if(senderId != null) {	// 로그인 값이 있는 경우만
			sessionList.remove(session);
		}
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		logger.info("=======================================알림전송 중 에러 발생=======================================");
	}
	
	private String getMemberId(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		String m_id = (String) httpSession.get("username"); // 세션에 저장된 username 기준 조회
		return m_id==null? null: m_id;
	}
	

	
	
	
}
	

