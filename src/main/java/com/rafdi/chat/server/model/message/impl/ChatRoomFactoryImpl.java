package com.rafdi.chat.server.model.message.impl;

import com.rafdi.chat.server.model.message.ChatRoom;
import com.rafdi.chat.server.model.message.ChatRoomFactory;
import com.rafdi.chat.server.model.message.InvalidChatRoomException;

public class ChatRoomFactoryImpl implements ChatRoomFactory {

	@Override
	public ChatRoom createChatRoom(String chatRoomName)
			throws InvalidChatRoomException {
		if (chatRoomName == null) {
			throw new InvalidChatRoomException(
					"chatroom's room name can't be null!");
		} else if (chatRoomName.equals("")) {
			throw new InvalidChatRoomException(
					"chatroom's room name can't be an empty string!");
		}
		ChatRoom chatRoom = new ChatRoom(chatRoomName);
		return chatRoom;
	}

}
