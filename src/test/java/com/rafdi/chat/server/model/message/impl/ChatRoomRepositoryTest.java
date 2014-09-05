package com.rafdi.chat.server.model.message.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.rafdi.chat.server.infra.ChatRoomDAO;
import com.rafdi.chat.server.model.message.ChatRoom;
import com.rafdi.chat.server.model.message.ChatRoomFactory;
import com.rafdi.chat.server.model.message.ChatRoomRepository;
import com.rafdi.chat.server.model.message.InvalidChatRoomException;
import com.rafdi.chat.server.model.message.InvalidChatRoomRepositoryException;

public class ChatRoomRepositoryTest {
	private ChatRoomRepository chatRoomRepository;
	private ChatRoomFactory chatRoomFactory;
	private ChatRoomDAO dao;

	@Before
	public void setUp() throws Exception {
		dao = Mockito.mock(ChatRoomDAO.class);
		chatRoomFactory = Mockito.mock(ChatRoomFactory.class);
		chatRoomRepository = new ChatRoomRepositoryImpl(chatRoomFactory, dao);
	}

	@After
	public void tearDown() throws Exception {
		chatRoomRepository = null;
	}

	@Test
	public void saveChatRoomSuccessful()
			throws InvalidChatRoomRepositoryException, InvalidChatRoomException {
		String chatRoomName = "chatroom1";
		ChatRoom expectedChatRoom = new ChatRoom(chatRoomName);
		Mockito.when(chatRoomFactory.createChatRoom(chatRoomName)).thenReturn(
				expectedChatRoom);

		chatRoomRepository.saveChatRoom(expectedChatRoom);
		Mockito.verify(dao, Mockito.times(1)).saveChatRoom(expectedChatRoom);

	}
}
