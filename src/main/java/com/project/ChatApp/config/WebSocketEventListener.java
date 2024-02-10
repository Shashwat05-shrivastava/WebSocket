package com.project.ChatApp.config;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.project.ChatApp.config.model.ChatMessage;
import com.project.ChatApp.config.model.MessageType;

import lombok.extern.slf4j.Slf4j;
//This class will send notification to other users in session if any user disconnected from the session
@Component
@Slf4j
public class WebSocketEventListener {
	
	private final SimpMessageSendingOperations messageTemplate=null;
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor=StompHeaderAccessor.wrap(event.getMessage());
		String username=(String) headerAccessor.getSessionAttributes().get("username");
		if(username!=null) {
			log.info("User disconnected :{}",username);
			var chatMessage=ChatMessage.builder()
					.type(MessageType.LEAVE)
					.sender(username)
					.build();
			messageTemplate.convertAndSend("topic/public", chatMessage);
		}
	}

}
