package com.rafdi.chat.model.user.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rafdi.chat.model.user.InvalidNameException;
import com.rafdi.chat.model.user.User;
import com.rafdi.chat.model.user.UserFactory;

public class UserFactoryTest {
	private UserFactory userFactory;

	@Before
	public void setUp() throws Exception {
		userFactory = new UserFactoryImpl();
	}

	@After
	public void tearDown() throws Exception {
		userFactory = null;
	}

	@Test
	public void testCreateUserWithName() {
		String expectedName = "Bambi";
		User user;
		try {
			user = userFactory.createUser(expectedName);
			Assert.assertNotNull("User should not be null", user);
			Assert.assertEquals("actual username does not match expected",
					expectedName, user.getName());
		} catch (InvalidNameException e) {

			e.printStackTrace();
		}

	}

	@Test(expected = InvalidNameException.class)
	public void testCreateUserWithNullNameThrowsException()
			throws InvalidNameException {
		String nullName = null;
		userFactory.createUser(nullName);

	}

	@Test(expected = InvalidNameException.class)
	public void testCreateUserWithSpacesThrowsInvalidNameException()
			throws InvalidNameException {
		String expectedName = "name with spaces";
		userFactory.createUser(expectedName);

	}

	@Test(expected = InvalidNameException.class)
	public void testCreateUserWithEmptyStringThrowsInvalidNameException()
			throws InvalidNameException {
		String expectedName = "";
		userFactory.createUser(expectedName);
	}

	@Test(expected = InvalidNameException.class)
	public void testCreateUserWithIllegalCharsThrowsInvalidNameException()
			throws InvalidNameException {
		String expectedName = "!@£$£$@";
		userFactory.createUser(expectedName);
	}

	@Test
	public void testCreateUserWithNameLongerThanLimitThrowsInvalidNameException() {

	}

}
