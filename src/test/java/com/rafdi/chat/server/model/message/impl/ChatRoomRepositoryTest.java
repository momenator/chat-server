package com.rafdi.chat.server.model.message.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rafdi.chat.server.infra.dao.ChatRoomDAO;
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
		dao = mock(ChatRoomDAO.class);
		chatRoomFactory = mock(ChatRoomFactory.class);
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
		when(chatRoomFactory.createChatRoom(chatRoomName)).thenReturn(
				expectedChatRoom);

		chatRoomRepository.saveChatRoom(expectedChatRoom);
		verify(dao, times(1)).saveChatRoom(expectedChatRoom);
	}

	@Test(expected = InvalidChatRoomException.class)
	public void testSaveChatRoomWithNullChatroomThrowsInvalidChatRoomExceptionClass()
			throws InvalidChatRoomException {
		ChatRoom expectedChatRoom = null;
		chatRoomRepository.saveChatRoom(expectedChatRoom);
		verify(dao, times(1)).saveChatRoom(expectedChatRoom);
	}

	@Test(expected = InvalidChatRoomException.class)
	public void testSaveChatRoomWithNullNameThrowsInvalidChatRoomExceptionClass()
			throws InvalidChatRoomException {
		String expectedChatRoomName = null;
		ChatRoom expectedChatRoom = new ChatRoom(expectedChatRoomName);
		when(chatRoomFactory.createChatRoom(expectedChatRoomName)).thenReturn(
				expectedChatRoom);
		chatRoomRepository.saveChatRoom(expectedChatRoom);
		verify(dao, times(1)).saveChatRoom(expectedChatRoom);
		assertTrue(expectedChatRoom.getChatRoomName().equals(
				expectedChatRoomName));
	}

	@Test
	public void testFindChatRoomByNameSuccessfully()
			throws InvalidChatRoomException, InvalidChatRoomRepositoryException {
		String expectedChatRoomName = "testChatRoom";
		ChatRoom expectedChatRoom = new ChatRoom(expectedChatRoomName);
		when(chatRoomFactory.createChatRoom(expectedChatRoomName)).thenReturn(
				expectedChatRoom);
		chatRoomRepository.saveChatRoom(expectedChatRoom);
		assertTrue(expectedChatRoom.getChatRoomName().equals(
				expectedChatRoomName));
		assertTrue(chatRoomRepository.findChatRoomByName(expectedChatRoomName)
				.equals(expectedChatRoom));
	}

	@Test(expected = InvalidChatRoomRepositoryException.class)
	public void testFindNullChatRoomNameThrowsInvalidChatRoomRepositoryException()
			throws InvalidChatRoomRepositoryException, InvalidChatRoomException {
		String expectedChatRoomName = null;
		chatRoomRepository.findChatRoomByName(expectedChatRoomName);
	}

}
