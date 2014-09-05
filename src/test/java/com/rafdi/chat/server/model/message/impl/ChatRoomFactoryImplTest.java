package com.rafdi.chat.server.model.message.impl;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rafdi.chat.server.model.message.ChatRoom;
import com.rafdi.chat.server.model.message.ChatRoomFactory;
import com.rafdi.chat.server.model.message.InvalidChatRoomException;

public class ChatRoomFactoryImplTest {
	private ChatRoomFactory chatRoomFactory;

	@Before
	public void setUp() throws Exception {
		chatRoomFactory = new ChatRoomFactoryImpl();
	}

	@After
	public void tearDown() throws Exception {
		chatRoomFactory = null;
	}

	@Test
	public void testCreateChatRoomSuccessful() throws InvalidChatRoomException {
		String chatRoomName = "chatroom1";
		ChatRoom chatRoom = chatRoomFactory.createChatRoom(chatRoomName);
		Assert.assertNotNull(chatRoom);
	}

}
