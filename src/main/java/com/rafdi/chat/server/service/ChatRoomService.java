package com.rafdi.chat.server.service;

import com.rafdi.chat.server.model.message.InvalidChatRoomException;
import com.rafdi.chat.server.model.message.InvalidMessageException;
import com.rafdi.chat.server.model.message.Message;
import com.rafdi.chat.server.model.user.User;

public interface ChatRoomService {
	public Message sendMessage(String message, String roomName, User user)
			throws InvalidMessageException, InvalidChatRoomException;
}
