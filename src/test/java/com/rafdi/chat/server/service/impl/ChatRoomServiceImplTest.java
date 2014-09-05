package com.rafdi.chat.server.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.rafdi.chat.server.model.message.ChatRoom;
import com.rafdi.chat.server.model.message.ChatRoomRepository;
import com.rafdi.chat.server.model.message.InvalidChatRoomException;
import com.rafdi.chat.server.model.message.InvalidMessageException;
import com.rafdi.chat.server.model.message.Message;
import com.rafdi.chat.server.model.message.MessageFactory;
import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.model.user.UserRepository;
import com.rafdi.chat.server.service.ChatRoomService;

public class ChatRoomServiceImplTest {
	private ChatRoomService chatRoomService;
	private ChatRoomRepository chatRoomRepository;
	private UserRepository userRepository;
	private MessageFactory messageFactory;

	@Before
	public void setUp() throws Exception {
		chatRoomRepository = Mockito.mock(ChatRoomRepository.class);
		messageFactory = Mockito.mock(MessageFactory.class);
		chatRoomService = new ChatRoomServiceImpl(chatRoomRepository,
				messageFactory);
	}

	@After
	public void tearDown() throws Exception {
		chatRoomService = null;
	}

	@Test
	public void sendMessageSuccessful() throws InvalidMessageException,
			InvalidChatRoomException {
		User user = new User("johnDoe", "password".getBytes());
		String roomName = "chatroom1";
		String message = "hello world";
		Mockito.when(chatRoomRepository.findChatRoomByName(roomName))
				.thenReturn(new ChatRoom(roomName));
		Mockito.when(messageFactory.createMessage(message, user)).thenReturn(
				new Message(message, user));
		chatRoomService.sendMessage(message, roomName, user);

	}

}
