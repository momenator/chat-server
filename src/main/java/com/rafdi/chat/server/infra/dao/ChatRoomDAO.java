package com.rafdi.chat.server.infra.dao;

import com.rafdi.chat.server.model.message.ChatRoom;
import com.rafdi.chat.server.model.message.Message;

public interface ChatRoomDAO {
	public ChatRoom getChatRoom(String chatRoomName);

	public void saveChatRoom(ChatRoom chatRoom);

	public void saveMessage(ChatRoom chatRoom, Message message);
}
