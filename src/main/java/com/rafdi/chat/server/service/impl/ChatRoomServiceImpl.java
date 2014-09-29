package com.rafdi.chat.server.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rafdi.chat.server.model.message.ChatRoom;
import com.rafdi.chat.server.model.message.ChatRoomFactory;
import com.rafdi.chat.server.model.message.ChatRoomRepository;
import com.rafdi.chat.server.model.message.InvalidChatRoomException;
import com.rafdi.chat.server.model.message.InvalidChatRoomRepositoryException;
import com.rafdi.chat.server.model.message.InvalidMessageException;
import com.rafdi.chat.server.model.message.Message;
import com.rafdi.chat.server.model.message.MessageFactory;
import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.service.ChatRoomService;

@Component
public class ChatRoomServiceImpl implements ChatRoomService {
	private ChatRoomRepository chatRoomRepository;
	private MessageFactory messageFactory;
	private ChatRoomFactory chatRoomFactory;

	@Autowired
	public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository,
			ChatRoomFactory chatRoomFactory, MessageFactory messageFactory) {
		this.chatRoomRepository = chatRoomRepository;
		this.chatRoomFactory = chatRoomFactory;
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

	@Override
	public ChatRoom createChatRoom(String chatRoomName)
			throws InvalidChatRoomException {
		ChatRoom chatRoom = chatRoomFactory.createChatRoom(chatRoomName);
		chatRoomRepository.saveChatRoom(chatRoom);
		try {
			chatRoom = chatRoomRepository.findChatRoomByName(chatRoom
					.getChatRoomName());
		} catch (InvalidChatRoomRepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chatRoom;
	}

	public ChatRoom getChatRoom(String chatRoomName)
			throws InvalidChatRoomException, InvalidChatRoomRepositoryException {
		ChatRoom chatRoom = chatRoomRepository.findChatRoomByName(chatRoomName);
		return chatRoom;
	}

}
