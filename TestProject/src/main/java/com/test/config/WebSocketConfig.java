
package com.test.config;

import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
		
	private final WebSocketHandler webSocketHandler;

	 @Override 
	 public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		 registry.addHandler(webSocketHandler, "/ws/echo")
		 .setAllowedOrigins("*")
		 .addInterceptors(new HandshakeInterceptor())
		 .withSockJS();
	 }	 
	
	 public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor{

			@Override
		    public boolean beforeHandshake(ServerHttpRequest request,ServerHttpResponse response, WebSocketHandler wsHandler,
		        Map<String, Object> attributes) throws Exception {    
		        String username = "";
		        Object principal = SecurityContextHolder.getContext().getAuthentication();
				if(principal!=null) {
					Object userDetail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					if(!userDetail.equals("anonymousUser")) {
						PrincipalDetails userData = (PrincipalDetails)userDetail;
						username = userData.getUsername();
					}
				}
		        attributes.put("username", username);
		        return super.beforeHandshake(request, response, wsHandler, attributes);
		    }
	 }
}
