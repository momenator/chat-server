package com.rafdi.chat.model.user;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserRepositoryTest {
	private UserRepository userRepository;

	@Before
	public void setUp() throws Exception {
		userRepository = new UserRespositoryImpl();
	}

	@After
	public void tearDown() throws Exception {
		userRepository = null;
	}

	@Test
	public void testSaveUser() {
		String name = "Bob";
		User user = new User(name);
		boolean saved = userRepository.saveUser(user);
		Assert.assertTrue("User is not saved", saved);
	}

	@Test
	public void testSaveNullUser() {

	}

}
