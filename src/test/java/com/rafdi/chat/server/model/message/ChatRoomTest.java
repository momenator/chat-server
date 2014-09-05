package com.rafdi.chat.server.model.message;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rafdi.chat.server.model.user.User;

public class ChatRoomTest {

	private ChatRoom chatRoom;

	@Before
	public void setUp() throws Exception {
		chatRoom = new ChatRoom("johnDoe");
	}

	@After
	public void tearDown() throws Exception {
		chatRoom = null;
	}

	@Test
	public void testAddMessageSuccessful() throws InvalidMessageException {
		String messageInput = "Hello World";
		User user = new User("johnDoe", "password".getBytes());
		Message message = new Message(messageInput, user);
		chatRoom.addMessage(message);
	}
}
