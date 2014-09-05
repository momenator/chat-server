package com.rafdi.chat.server.service.impl;

import com.rafdi.chat.server.model.message.ChatRoom;
import com.rafdi.chat.server.model.message.ChatRoomRepository;
import com.rafdi.chat.server.model.message.InvalidChatRoomException;
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
	public Message sendMessage(String text, String roomName, User user)
			throws InvalidMessageException, InvalidChatRoomException {
		Message message = messageFactory.createMessage(text, user);
		ChatRoom chatRoom = chatRoomRepository.findChatRoomByName(roomName);
		chatRoom.addMessage(message);
		chatRoomRepository.saveChatRoom(chatRoom);
		return message;
	}

}
