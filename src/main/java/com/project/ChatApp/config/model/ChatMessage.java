package com.project.ChatApp.config.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
	private String content;
	private String sender;
	private MessageType type;
}
