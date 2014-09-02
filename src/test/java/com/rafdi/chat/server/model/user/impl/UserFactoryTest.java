package com.rafdi.chat.server.model.user.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rafdi.chat.server.model.user.InvalidNameException;
import com.rafdi.chat.server.model.user.InvalidPassException;
import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.model.user.UserFactory;

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
			user = userFactory.createUser(expectedName,
					"muhammad123".getBytes());
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
		userFactory.createUser(nullName, "muhammad123".getBytes());

	}

	@Test(expected = InvalidNameException.class)
	public void testCreateUserWithSpacesThrowsInvalidNameException()
			throws InvalidNameException {
		String expectedName = "name with spaces";
		userFactory.createUser(expectedName, "muhammad123".getBytes());

	}

	@Test(expected = InvalidNameException.class)
	public void testCreateUserWithEmptyStringThrowsInvalidNameException()
			throws InvalidNameException {
		String expectedName = "";
		userFactory.createUser(expectedName, "muhammad123".getBytes());
	}

	@Test(expected = InvalidNameException.class)
	public void testCreateUserWithIllegalCharsThrowsInvalidNameException()
			throws InvalidNameException {
		String expectedName = "!@£$£$@";
		userFactory.createUser(expectedName, "muhammad123".getBytes());
	}

	@Test(expected = InvalidNameException.class)
	public void testCreateUserWithNameLongerThanLimitThrowsInvalidNameException()
			throws InvalidNameException {
		String expectedName = "dsfasgrfwf4fw32r24t3rgf3g34rg35g43g34fr4gf";
		userFactory.createUser(expectedName, "muhammad123".getBytes());

	}

	@Test(expected = InvalidPassException.class)
	public void testCreateUserWithNullPassThrowsInvalidPassException()
			throws InvalidPassException {
		String password = null;
		userFactory.createUser("testUser", null);
	}

	@Test(expected = InvalidPassException.class)
	public void testCreateUserWithPassLessThan6charsThrowsInvalidPassException()
			throws InvalidPassException {
		String password = "123";
		byte[] passEnc = password.getBytes();
		System.out.println(new String(passEnc));
		userFactory.createUser("testUser", password.getBytes());
	}
}
