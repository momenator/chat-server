package com.rafdi.chat.server.model.message;

public interface ChatRoomRepository {
	public void saveChatRoom(ChatRoom chatRoom) throws InvalidChatRoomException;

	public ChatRoom findChatRoomByName(String chatRoomName)
			throws InvalidChatRoomException;
}