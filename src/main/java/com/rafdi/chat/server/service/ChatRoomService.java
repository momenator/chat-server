package com.rafdi.chat.server.service;

import java.util.List;

import com.rafdi.chat.server.model.message.ChatRoom;
import com.rafdi.chat.server.model.message.InvalidChatRoomException;
import com.rafdi.chat.server.model.message.InvalidChatRoomRepositoryException;
import com.rafdi.chat.server.model.message.InvalidMessageException;
import com.rafdi.chat.server.model.message.Message;
import com.rafdi.chat.server.model.user.User;

public interface ChatRoomService {
	public Message sendMessage(String message, String roomName, User user)
			throws InvalidMessageException, InvalidChatRoomException,
			InvalidChatRoomRepositoryException;

	public List<Message> getMessages(String roomName);

	public ChatRoom createChatRoom(String chatRoomName)
			throws InvalidChatRoomException;

	public ChatRoom getChatRoom(String chatRoomName)
			throws InvalidChatRoomException, InvalidChatRoomRepositoryException;
}
