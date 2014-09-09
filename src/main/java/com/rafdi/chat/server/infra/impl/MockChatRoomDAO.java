package com.rafdi.chat.server.infra.impl;

import com.rafdi.chat.server.infra.ChatRoomDAO;
import com.rafdi.chat.server.model.message.ChatRoom;

public class MockChatRoomDAO implements ChatRoomDAO {

	@Override
	public ChatRoom getChatRoom(String chatRoomName) {
		return null;
	}

	@Override
	public void saveChatRoom(ChatRoom chatRoom) {

	}

}
