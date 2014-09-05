package com.rafdi.chat.server.model.message.impl;

import com.rafdi.chat.server.infra.ChatRoomDAO;
import com.rafdi.chat.server.model.message.ChatRoom;
import com.rafdi.chat.server.model.message.ChatRoomFactory;
import com.rafdi.chat.server.model.message.ChatRoomRepository;
import com.rafdi.chat.server.model.message.InvalidChatRoomException;

public class ChatRoomRepositoryImpl implements ChatRoomRepository {

	private ChatRoomDAO dao;
	private ChatRoomFactory chatRoomFactory;

	public ChatRoomRepositoryImpl(ChatRoomFactory chatRoomFactory,
			ChatRoomDAO dao) {
		this.dao = dao;
		this.chatRoomFactory = chatRoomFactory;
	}

	@Override
	public void saveChatRoom(ChatRoom chatRoom) throws InvalidChatRoomException {
		if (chatRoom == null) {
			throw new InvalidChatRoomException("Chat room can't be null");
		}
		dao.saveChatRoom(chatRoom);
	}

	@Override
	public ChatRoom findChatRoomByName(String chatRoomName)
			throws InvalidChatRoomException {
		ChatRoom chatRoom = dao.getChatRoom(chatRoomName);
		if (chatRoom == null) {
			chatRoom = chatRoomFactory.createChatRoom(chatRoomName);
			dao.saveChatRoom(chatRoom);
		}
		return chatRoom;
	}

}
