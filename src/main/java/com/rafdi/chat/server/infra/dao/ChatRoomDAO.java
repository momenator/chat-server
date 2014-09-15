package com.rafdi.chat.server.infra.dao;

import com.rafdi.chat.server.model.message.ChatRoom;

public interface ChatRoomDAO {
	public ChatRoom getChatRoom(String chatRoomName);

	public void saveChatRoom(ChatRoom chatRoom);
}
