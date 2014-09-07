package com.rafdi.chat.server.model.user;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	User user;

	@Before
	public void setUp() throws Exception {
		user = new User("testName", "testPass".getBytes());
	}

	@After
	public void tearDown() throws Exception {
		user = null;
	}

	@Test
	public void testToStringMethodReturnsNameSuccessfully() {
		String expectedName = user.toString();
		Assert.assertEquals("user name did not match", expectedName,
				user.getName());
	}

}
