package com.rafdi.chat.server.model.user.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rafdi.chat.server.infra.UserDAO;
import com.rafdi.chat.server.infra.impl.MockUserDAO;
import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.model.user.UserRepository;

public class UserRepositoryTest {
	private UserRepository userRepository;
	private UserDAO userDao = new MockUserDAO();

	@Before
	public void setUp() throws Exception {
		userRepository = new UserRepositoryImpl(userDao);
	}

	@After
	public void tearDown() throws Exception {
		userRepository = null;
	}

	@Test
	public void testSuccessfullySaveUser() {
		String name = "johnDoe";
		User user = new User(name, null);
		boolean saved = userRepository.saveUser(user);
		Assert.assertNotNull("The user can't be null!", user);
		Assert.assertEquals(saved, true);
	}

	@Test(expected = NullPointerException.class)
	public void saveNullUserThrowsNullPointerException()
			throws NullPointerException {
		User user = null;
		userRepository.saveUser(null);
		Assert.assertTrue(user == null);
	}

	@Test
	public void testSuccessfullyFindUserByName() {
		String name = "Bobby";
		User expectedUser = new User(name, null);
		userRepository.saveUser(expectedUser);
		User actualUser = userRepository.findUserByName(name);
		Assert.assertTrue("the user did not match!",
				expectedUser.equals(actualUser));
	}

	@Test(expected = NullPointerException.class)
	public void testFailToFindUserByNameThrowsInvalidNullPointerException()
			throws NullPointerException {
		User user = userRepository.findUserByName("GHOST");
		Assert.assertNull(user);
	}

	@Test(expected = NullPointerException.class)
	public void testFindNullUserNameThrowsNullPointerException()
			throws NullPointerException {
		String expectedName = null;
		userRepository.findUserByName(expectedName);
		Assert.assertTrue(expectedName.equals(null));
	}

}
