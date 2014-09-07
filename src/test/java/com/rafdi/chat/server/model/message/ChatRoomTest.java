package com.rafdi.chat.server.model.message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rafdi.chat.server.model.user.User;

public class ChatRoomTest {

	private ChatRoom chatRoom;

	@Before
	public void setUp() throws Exception {
		chatRoom = new ChatRoom("TESTCHAT");
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

	@Test(expected = InvalidMessageException.class)
	public void testAddNullMessageThrowsInvalidMessageException()
			throws InvalidMessageException {
		Message expectedMessage = null;
		chatRoom.addMessage(expectedMessage);
		Assert.assertTrue(expectedMessage.equals(null));
	}

	@Test
	public void testGetChatRoomNameSuccessful() throws InvalidChatRoomException {
		String expectedChatRoomName = "johnDoe";
		chatRoom.setChatRoomName(expectedChatRoomName);
		Assert.assertTrue(chatRoom.getChatRoomName().equals(
				expectedChatRoomName));
	}

	@Test
	public void testGetMessagesSuccessfulAndReturnsMessages()
			throws InvalidMessageException {
		User user = new User("testUser", "testUser".getBytes());
		Message message1 = new Message("message1", user);
		Message message2 = new Message("message2", user);
		chatRoom.addMessage(message1);
		chatRoom.addMessage(message2);
		List<Message> actualMessages = new ArrayList<Message>();
		actualMessages.add(message1);
		actualMessages.add(message2);
		Assert.assertEquals(chatRoom.getMessages(), actualMessages);
	}

	@Test
	public void testGetUsersSuccessfulAndReturnsUsers() {
		User user1 = new User("johnDoe", "testPass12".getBytes());
		User user2 = new User("johnDoe1", "testPass12".getBytes());
		Set<User> actualUsers = new HashSet<User>();
		actualUsers.add(user1);
		actualUsers.add(user2);
		chatRoom.setUsers(actualUsers);
		Assert.assertEquals(chatRoom.getUsers(), actualUsers);
	}

	@Test
	public void testAddUserSuccessfully() {
		User user = new User("johnDoe", "testPass12".getBytes());
		chatRoom.addUser(user);
		Assert.assertNotNull(user);
	}

	// bad case 1 : chatroom name is null
	// bad case 2 : set users with null
	// bad case 3 : set messages with null
	@Test(expected = InvalidChatRoomException.class)
	public void testSetChatRoomToNullThrowsInvalidChatRoomException()
			throws InvalidChatRoomException {
		chatRoom.setChatRoomName(null);
		Assert.assertTrue(chatRoom.getChatRoomName().equals(null));
	}

}
