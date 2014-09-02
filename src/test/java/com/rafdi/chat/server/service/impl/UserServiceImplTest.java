package com.rafdi.chat.server.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.model.user.UserFactory;
import com.rafdi.chat.server.model.user.UserRepository;

public class UserServiceImplTest {
	private UserFactory userFactory;
	private UserRepository userRepository;
	private UserServiceImpl userServiceImpl;

	@Before
	public void setUp() throws Exception {
		userFactory = Mockito.mock(UserFactory.class);
		userRepository = Mockito.mock(UserRepository.class);
		userServiceImpl = new UserServiceImpl(userFactory, userRepository);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAuthenticateUserWhenUserDontExist() throws Exception {
		String name = "johndoe";
		byte[] password = "password".getBytes();
		boolean expectedValue = false;

		Mockito.when(userRepository.findUserByName(name)).thenReturn(null);

		boolean actualValue = userServiceImpl.authenticateUser(name, password);

		Mockito.verify(userRepository).findUserByName(name);

		Assert.assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testAuthenticateUserWhenUserExist() throws Exception {
		String name = "johndoe";
		byte[] password = "password".getBytes();
		boolean expectedValue = true;

		User user = new User(name, password);
		Mockito.when(userRepository.findUserByName(name)).thenReturn(user);

		boolean actualValue = userServiceImpl.authenticateUser(name, password);

		Mockito.verify(userRepository).findUserByName(name);

		Assert.assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testCreateUser() throws Exception {
		String name = "johndoe";
		byte[] password = "password".getBytes();
		User expectedUser = new User(name, password);
		Mockito.when(userFactory.createUser(name, password)).thenReturn(
				expectedUser);

		User actualUser = userServiceImpl.createUser(name, password);
		Mockito.verify(userRepository).saveUser(expectedUser);
		Assert.assertNotNull(actualUser);
		Assert.assertEquals(expectedUser, actualUser);
	}

}
