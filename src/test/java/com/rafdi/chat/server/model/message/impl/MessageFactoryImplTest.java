package com.rafdi.chat.server.model.message.impl;

import java.util.Date;

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

	@Test(expected = InvalidMessageException.class)
	public void testCreateMessageLongerThanCharLimitThrowsInvalidMessageException()
			throws InvalidMessageException {
		String expectedMessage = "asgrgabrlgfewirgreibfksfhgvgeruwgferufbreyufgruefgkrhfvrkfj"
				+ "gekrufvehjkrfverukygrvksfvehjrsgvseuygveruhkgvfjsbvsbvjhfguryegfuewgvkrhefhrf"
				+ "gerhfhrebfhgrkgreuywgerwyugweurfvirwgbfgskgfwregewrygfwerigfhrbfhsbgsagbsfgkkerghw"
				+ "ergewyiergryiwgfihrlbflkfbklrbalkbilgbigrberilgbleirgkjrsbgfkndblgwieurgiew";
		User user = new User("johnDoe", "password".getBytes());
		Message actualMessage = messageFactory.createMessage(expectedMessage,
				user);
		Assert.assertTrue(expectedMessage.length() > 250);
	}

	@Test
	public void testCreateValidMessage() throws InvalidMessageException {
		String expectedMessage = "Hello world";
		User user = new User("johnDoe", "password".getBytes());
		Message expectedMsgObj = new Message(expectedMessage, user);

		Message actualMessage = messageFactory.createMessage(expectedMessage,
				user);
		Assert.assertNotNull(actualMessage);
		Assert.assertEquals(expectedMsgObj.getUser(), actualMessage.getUser());
		Assert.assertEquals(expectedMsgObj.getMessage(),
				actualMessage.getMessage());
	}

	@Test
	public void testCreateValidMessageMatchesMessageContent()
			throws InvalidMessageException {
		String expectedMessageContent = "hellow world";
		User user = new User("johnDoe", "password".getBytes());
		Message expectedMessage = new Message(expectedMessageContent, user);
		Assert.assertTrue(expectedMessage.getMessage().equals(
				expectedMessageContent));
	}

	@Test
	public void testCreateMessageMatchesUser() throws InvalidMessageException {
		String expectedMessageContent = "hellow world";
		User user = new User("johnDoe", "password".getBytes());
		Message expectedMessage = new Message(expectedMessageContent, user);
		Assert.assertTrue(expectedMessage.getUser().equals(user));
	}

	@Test(expected = InvalidMessageException.class)
	public void testCreateMessageWithNullUserThrowsInvalidMessageException()
			throws InvalidMessageException {
		String expectedMessageContent = "hellow world";
		User expectedUser = null;
		Message expectedMessage = new Message(expectedMessageContent,
				expectedUser);
		Assert.assertTrue(expectedMessage.getUser().equals(expectedUser));
	}

	@Test
	public void testGetTimestampReturnTimeStampSuccessfully()
			throws InvalidMessageException {
		User user = new User("johnDoe", "password".getBytes());
		Message expectedMessage = new Message("hellow world", user);
		Date expectedTimestamp = expectedMessage.getTimestamp();
		Assert.assertTrue(expectedTimestamp.equals(expectedMessage
				.getTimestamp()));
	}
}
