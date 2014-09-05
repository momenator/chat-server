package com.rafdi.chat.server.model.message.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rafdi.chat.server.model.message.InvalidMessageException;
import com.rafdi.chat.server.model.message.Message;
import com.rafdi.chat.server.model.message.MessageFactory;
import com.rafdi.chat.server.model.user.User;

public class MessageFactoryImplTest {
	MessageFactory messageFactory;

	@Before
	public void setUp() throws Exception {
		messageFactory = new MessageFactoryImpl();
	}

	@After
	public void tearDown() throws Exception {
		messageFactory = null;
	}

	@Test(expected = InvalidMessageException.class)
	public void testCreateNullMessageThrowsInvalidMessageException()
			throws InvalidMessageException {
		String expectedMessage = null;
		User user = new User("johnDoe", "password".getBytes());
		Message actualMessage = messageFactory.createMessage(expectedMessage,
				user);
		Assert.assertNotNull(actualMessage);
	}

	@Test
	public void testCreateValidMessage() throws InvalidMessageException {
		String expectedMessage = "Hello world";
		User user = new User("johnDoe", "password".getBytes());
		Message expectedMsgObj = new Message(expectedMessage, user);

		Message actualMessage = messageFactory.createMessage(expectedMessage,
				user);
		Assert.assertNotNull(actualMessage);
		Assert.assertEquals(expectedMsgObj, actualMessage);
	}
}
