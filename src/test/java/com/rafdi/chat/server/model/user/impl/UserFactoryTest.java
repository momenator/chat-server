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
	public void testCreateUserWithValidName() {
		String expectedName = "Bambi";
		User user;

		user = userFactory.createUser(expectedName, "muhammad123".getBytes());
		Assert.assertNotNull("User should not be null", user);
		Assert.assertEquals("actual username does not match expected",
				expectedName, user.getName());
	}

	@Test(expected = InvalidNameException.class)
	public void testCreateUserWithNullNameThrowsException()
			throws InvalidNameException {
		String nullName = null;
		User expectedUser = userFactory.createUser(nullName,
				"muhammad123".getBytes());
		Assert.assertEquals(expectedUser.getName(), null);
	}

	@Test(expected = InvalidNameException.class)
	public void testCreateUserWithSpacesThrowsInvalidNameException()
			throws InvalidNameException {
		String expectedName = "name with spaces";
		User expectedUser = userFactory.createUser(expectedName,
				"muhammad123".getBytes());
		Assert.assertTrue(expectedUser.getName().contains(" "));
	}

	@Test(expected = InvalidNameException.class)
	public void testCreateUserWithEmptyStringThrowsInvalidNameException()
			throws InvalidNameException {
		String expectedName = "";
		User expectedUser = userFactory.createUser(expectedName,
				"muhammad123".getBytes());
		Assert.assertTrue(expectedUser.getName().equals(""));
	}

	@Test(expected = InvalidNameException.class)
	public void testCreateUserWithIllegalCharsThrowsInvalidNameException()
			throws InvalidNameException {
		String expectedName = "!@£$£$@";
		User expectedUser = userFactory.createUser(expectedName,
				"muhammad123".getBytes());
		boolean isAlphanumeric = true;
		for (char c : expectedUser.getName().toCharArray()) {
			if (!Character.isLetterOrDigit(c)) {
				isAlphanumeric = false;
			}
		}
		Assert.assertTrue(false == isAlphanumeric);
	}

	@Test(expected = InvalidNameException.class)
	public void testCreateUserWithNameLongerThanLimitThrowsInvalidNameException()
			throws InvalidNameException {
		String expectedName = "dsfasgrfwf4fw32r24t3rgf3g34rg35g43g34fr4gf";
		User expectedUser = userFactory.createUser(expectedName,
				"muhammad123".getBytes());
		Assert.assertTrue(expectedUser.getName().length() > 20);
	}

	@Test
	public void testCreateUserWithValidPass() {
		String expectedPassword = "foobar123";
		User expectedUser = userFactory.createUser("foobar",
				expectedPassword.getBytes());
		Assert.assertNotNull(new String(expectedUser.getPassword()));
		Assert.assertTrue(new String(expectedUser.getPassword())
				.equals(expectedPassword));
	}

	@Test(expected = InvalidPassException.class)
	public void testCreateUserWithNullPassThrowsInvalidPassException()
			throws InvalidPassException {

		User expectedUser = userFactory.createUser("testUser", null);
		Assert.assertTrue(expectedUser.getPassword() == null);
	}

	@Test(expected = InvalidPassException.class)
	public void testCreateUserWithPassLessThan6charsThrowsInvalidPassException()
			throws InvalidPassException {
		String expectedPassword = "123";
		byte[] passEnc = expectedPassword.getBytes();
		User expectedUser = userFactory.createUser("testUser", passEnc);
		Assert.assertTrue(new String(expectedUser.getPassword()).length() < 6);
	}

	@Test(expected = InvalidPassException.class)
	public void testCreateUserWithLessThan2NumericCharsThrowsInvalidPassException()
			throws InvalidPassException {
		String expectedPassword = "foobar";
		byte[] passEnc = expectedPassword.getBytes();
		User expectedUser = userFactory.createUser("test", passEnc);

		int numCount = 0;
		String numbers = "01233456789";
		char[] letters = new String(expectedUser.getPassword()).toCharArray();
		for (char c : letters) {
			if (numbers.indexOf(c) > -1) {
				numCount++;
			}
		}
		Assert.assertTrue(numCount < 2);
	}
}
