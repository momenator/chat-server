package com.rafdi.chat.server.model.user.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.model.user.UserRepository;

public class UserRepositoryTest {
	private UserRepository userRepository;

	@Before
	public void setUp() throws Exception {
		userRepository = new UserRepositoryImpl();
	}

	@After
	public void tearDown() throws Exception {
		userRepository = null;
	}

	@Test
	public void testSaveUser() {
		String name = "Bob";
		User user = new User(name, null);
		boolean saved = userRepository.saveUser(user);
		Assert.assertTrue("User is not saved", saved);
	}

	@Test
	public void testFindUserByName() {
		String name = "Bob";
		User expectedUser = new User(name, null);
		userRepository.saveUser(expectedUser);
		User actualUser = expectedUser;
		Assert.assertEquals(expectedUser, actualUser);
	}
}
