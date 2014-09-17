package com.rafdi.chat.server.service.impl;

import java.util.Collections;
import java.util.List;

import com.rafdi.chat.server.model.message.ChatRoom;
import com.rafdi.chat.server.model.message.ChatRoomRepository;
import com.rafdi.chat.server.model.message.InvalidChatRoomException;
import com.rafdi.chat.server.model.message.InvalidChatRoomRepositoryException;
import com.rafdi.chat.server.model.message.InvalidMessageException;
import com.rafdi.chat.server.model.message.Message;
import com.rafdi.chat.server.model.message.MessageFactory;
import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.service.ChatRoomService;

public class ChatRoomServiceImpl implements ChatRoomService {
	private ChatRoomRepository chatRoomRepository;
	private MessageFactory messageFactory;

	public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository,
			MessageFactory messageFactory) {
		this.chatRoomRepository = chatRoomRepository;
		this.messageFactory = messageFactory;
	}

	@Override
	public List<Message> getMessages(String roomName) {
		ChatRoom chatRoom = null;
		try {
			chatRoom = chatRoomRepository.findChatRoomByName(roomName);
		} catch (InvalidChatRoomException e) {
			e.printStackTrace();
		} catch (InvalidChatRoomRepositoryException e) {
			e.printStackTrace();
		}
		if (chatRoom != null) {
			return chatRoom.getMessages();
		}
		return Collections.EMPTY_LIST;
	}

	@Override
	public Message sendMessage(String text, String roomName, User user)
			throws InvalidMessageException, InvalidChatRoomException,
			InvalidChatRoomRepositoryException {
		Message message = messageFactory.createMessage(text, user);
		ChatRoom chatRoom = chatRoomRepository.findChatRoomByName(roomName);
		chatRoomRepository.saveMessage(chatRoom, message);
		return message;
	}

}
