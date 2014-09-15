package com.rafdi.chat.server.ui;

import static junit.framework.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.service.UserService;

public class ChatAppTest {
	private User user;
	private UserService userService;

	@Before
	public void setUp() throws Exception {
		this.user = new User("testUser", "pass123".getBytes());
		this.userService = Mockito.mock(UserService.class);
	}

	@After
	public void tearDown() throws Exception {
		user = null;
	}

	@Test
	public void testRegisterUserSuccessfully() {
		Mockito.when(userService.createUser(user.getName(), user.getPassword()))
				.thenReturn(user);
		assertTrue(user.getName().equals("testUser"));
	}

}
