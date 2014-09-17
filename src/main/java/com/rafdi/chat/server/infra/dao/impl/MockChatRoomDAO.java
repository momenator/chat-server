package com.rafdi.chat.server.infra.dao.impl;

import com.rafdi.chat.server.infra.dao.ChatRoomDAO;
import com.rafdi.chat.server.model.message.ChatRoom;
import com.rafdi.chat.server.model.message.Message;

public class MockChatRoomDAO implements ChatRoomDAO {

	@Override
	public ChatRoom getChatRoom(String chatRoomName) {
		return null;
	}

	@Override
	public void saveChatRoom(ChatRoom chatRoom) {

	}

	@Override
	public void saveMessage(ChatRoom chatRoom, Message message) {
		// TODO Auto-generated method stub

	}

}
