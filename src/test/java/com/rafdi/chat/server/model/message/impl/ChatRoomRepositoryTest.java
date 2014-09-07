package com.rafdi.chat.server.model.message.impl;

import org.junit.After;
import org.junit.Assert;
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
	public void testSaveChatRoomSuccessful()
			throws InvalidChatRoomRepositoryException, InvalidChatRoomException {
		String chatRoomName = "chatroom1";
		ChatRoom expectedChatRoom = new ChatRoom(chatRoomName);
		Mockito.when(chatRoomFactory.createChatRoom(chatRoomName)).thenReturn(
				expectedChatRoom);

		chatRoomRepository.saveChatRoom(expectedChatRoom);
		Mockito.verify(dao, Mockito.times(1)).saveChatRoom(expectedChatRoom);
	}

	@Test(expected = InvalidChatRoomException.class)
	public void testSaveChatRoomWithNullChatroomThrowsInvalidChatRoomExceptionClass()
			throws InvalidChatRoomException {
		ChatRoom expectedChatRoom = null;
		chatRoomRepository.saveChatRoom(expectedChatRoom);
		Mockito.verify(dao, Mockito.times(1)).saveChatRoom(expectedChatRoom);
	}

	@Test(expected = InvalidChatRoomException.class)
	public void testSaveChatRoomWithNullNameThrowsInvalidChatRoomExceptionClass()
			throws InvalidChatRoomException {
		String expectedChatRoomName = null;
		ChatRoom expectedChatRoom = new ChatRoom(expectedChatRoomName);
		Mockito.when(chatRoomFactory.createChatRoom(expectedChatRoomName))
				.thenReturn(expectedChatRoom);
		chatRoomRepository.saveChatRoom(expectedChatRoom);
		Mockito.verify(dao, Mockito.times(1)).saveChatRoom(expectedChatRoom);
		Assert.assertTrue(expectedChatRoom.getChatRoomName().equals(
				expectedChatRoomName));
	}

	@Test
	public void testFindChatRoomByNameSuccessfully()
			throws InvalidChatRoomException, InvalidChatRoomRepositoryException {
		String expectedChatRoomName = "testChatRoom";
		ChatRoom expectedChatRoom = new ChatRoom(expectedChatRoomName);
		Mockito.when(chatRoomFactory.createChatRoom(expectedChatRoomName))
				.thenReturn(expectedChatRoom);
		chatRoomRepository.saveChatRoom(expectedChatRoom);
		Assert.assertTrue(expectedChatRoom.getChatRoomName().equals(
				expectedChatRoomName));
		Assert.assertTrue(chatRoomRepository.findChatRoomByName(
				expectedChatRoomName).equals(expectedChatRoom));
	}

	@Test(expected = InvalidChatRoomRepositoryException.class)
	public void testFindNullChatRoomNameThrowsInvalidChatRoomRepositoryException()
			throws InvalidChatRoomRepositoryException, InvalidChatRoomException {
		String expectedChatRoomName = null;
		chatRoomRepository.findChatRoomByName(expectedChatRoomName);
	}

}
