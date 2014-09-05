package com.rafdi.chat.server.model.message;

public interface ChatRoomFactory {
	public ChatRoom createChatRoom(String chatRoomName)
			throws InvalidChatRoomException;
}
