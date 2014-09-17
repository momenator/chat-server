package com.rafdi.chat.server.ui;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.InputStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.service.ChatRoomService;
import com.rafdi.chat.server.service.UserService;

public class ChatAppTest {
	private ChatApp chatApp;
	private ChatRoomService chatRoomService;
	private UserService userService;
	private InputStream inputStream;

	@Before
	public void setUp() throws Exception {
		chatApp = new ChatApp();
		chatRoomService = mock(ChatRoomService.class);
		userService = mock(UserService.class);
		inputStream = mock(InputStream.class);
		// chatRoomService = new ChatRoomServiceImpl();

	}

	@After
	public void tearDown() throws Exception {
		chatApp = null;
	}

	@Test
	public void testSetChatRoomServiceSuccessfully() {
		/*
		 * ChatRoomFactory chatRoomFactory = new ChatRoomFactoryImpl();
		 * ChatRoomDAO chatRoomdao = new MockChatRoomDAO(); ChatRoomRepository
		 * chatRoomRepository = new ChatRoomRepositoryImpl( chatRoomFactory,
		 * chatRoomdao); MessageFactory messageFactory = new
		 * MessageFactoryImpl(); ChatRoomService chatRoomService = new
		 * ChatRoomServiceImpl( chatRoomRepository, messageFactory);
		 */
		assertTrue(chatRoomService.equals(chatApp
				.setChatRoomService(chatRoomService)));
	}

	@Test
	public void testSetUserServiceSuccessfully() {
		// UserService user
		assertTrue(userService.equals(chatApp.setUserService(userService)));
	}

	@Test
	public void testSetIputStreamSuccessfully() {
		assertTrue(inputStream.equals(chatApp.setInputStream(inputStream)));
	}

	@Test
	public void testRegisterUserSuccessfully() {
		Scanner scanner = new Scanner("name \npassword");
		String expectedName = scanner.nextLine();
		String expectedPassword = scanner.nextLine();
		byte[] expectedPasswordEnc = expectedPassword.getBytes();
		User actualUser = new User(expectedName, expectedPasswordEnc);
		Mockito.when(userService.createUser(expectedName, expectedPasswordEnc))
				.thenReturn(actualUser);

		Mockito.when(userService.getUser(expectedName)).thenReturn(actualUser);
		assertTrue(actualUser.equals(userService.getUser(expectedName)));
	}
}
