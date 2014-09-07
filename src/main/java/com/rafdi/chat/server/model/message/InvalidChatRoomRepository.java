package com.rafdi.chat.server.model.message;

public class InvalidChatRoomRepository extends Exception {
	public InvalidChatRoomRepository(String message) {
		super(message);
	}
}
