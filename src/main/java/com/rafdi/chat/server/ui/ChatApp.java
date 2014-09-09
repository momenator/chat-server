package com.rafdi.chat.server.ui;

import java.util.Scanner;

import com.rafdi.chat.server.infra.ChatRoomDAO;
import com.rafdi.chat.server.infra.UserDAO;
import com.rafdi.chat.server.infra.impl.MockChatRoomDAO;
import com.rafdi.chat.server.infra.impl.MockUserDAO;
import com.rafdi.chat.server.model.message.ChatRoomFactory;
import com.rafdi.chat.server.model.message.InvalidChatRoomException;
import com.rafdi.chat.server.model.message.InvalidChatRoomRepositoryException;
import com.rafdi.chat.server.model.message.InvalidMessageException;
import com.rafdi.chat.server.model.message.Message;
import com.rafdi.chat.server.model.message.MessageFactory;
import com.rafdi.chat.server.model.message.impl.ChatRoomFactoryImpl;
import com.rafdi.chat.server.model.message.impl.ChatRoomRepositoryImpl;
import com.rafdi.chat.server.model.message.impl.MessageFactoryImpl;
import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.model.user.UserFactory;
import com.rafdi.chat.server.model.user.UserRepository;
import com.rafdi.chat.server.model.user.impl.UserFactoryImpl;
import com.rafdi.chat.server.model.user.impl.UserRepositoryImpl;
import com.rafdi.chat.server.service.ChatRoomService;
import com.rafdi.chat.server.service.UserService;
import com.rafdi.chat.server.service.impl.ChatRoomServiceImpl;
import com.rafdi.chat.server.service.impl.UserServiceImpl;

public class ChatApp {
	private static User user;

	public static void main(String[] args) {
		if (args.length > 0) {
			Scanner scanner = new Scanner(System.in);
			UserFactory userFactory = new UserFactoryImpl();
			ChatRoomDAO chatDao = new MockChatRoomDAO();
			UserDAO userDao = new MockUserDAO();
			UserRepository userRepository = new UserRepositoryImpl(userDao);
			MessageFactory messageFactory = new MessageFactoryImpl();
			ChatRoomFactory chatRoomFactory = new ChatRoomFactoryImpl();
			ChatRoomRepositoryImpl chatRoomRepository = new ChatRoomRepositoryImpl(
					chatRoomFactory, chatDao);

			UserServiceImpl userService = new UserServiceImpl(userFactory,
					userRepository);

			ChatRoomService chatRoomService = new ChatRoomServiceImpl(
					chatRoomRepository, messageFactory);
			while (true) {
				System.out.println("choose: register, login or chat");
				String action = scanner.nextLine();
				if ("register".equals(action.toLowerCase())) {
					registerUser(scanner, userService);
				} else if ("login".equals(action.toLowerCase())) {
					user = login(scanner, userService);
				} else if ("chat".equals(action.toLowerCase())) {
					if (user != null) {
						chat(scanner, chatRoomService);
					} else {
						user = login(scanner, userService);
						if (user != null) {
							chat(scanner, chatRoomService);
						}
					}
				} else {
					System.out
							.println("I don't get what you mean by " + action);
				}
			}
		} else {
			throw new IllegalArgumentException("Please specify your input!");
		}
	}

	private static void chat(Scanner scanner, ChatRoomService chatRoomService) {
		while (true) {
			String fullMessage = scanner.nextLine();
			if ("quit".equals(fullMessage)) {
				System.out.println("bye!");
				break;
			}
			String[] messageParts = fullMessage.split("#");
			String roomName = messageParts[0];
			String messageContent = messageParts[1];
			try {
				Message message = chatRoomService.sendMessage(messageContent,
						roomName, user);
				System.out.println(message.getMessage() + "  ..sent..");
			} catch (InvalidMessageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidChatRoomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidChatRoomRepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void registerUser(Scanner scanner,
			UserServiceImpl userService) {
		System.out.println("---WELCOME TO CHATAPP---");
		System.out.println("Please register");
		System.out.print("Please enter your name: ");
		String name = scanner.nextLine();
		System.out.print("Please enter your pass: ");
		String password = scanner.nextLine();
		byte[] passwordByte = password.getBytes();
		User user = userService.createUser(name, passwordByte);
		System.out.println("User " + user.getName() + " created successfully");

		System.out.println("-----");
		System.out.println("-----");
		System.out.println("-----");
	}

	private static User login(Scanner scanner, UserService userService) {
		System.out.println("Login");
		System.out.print("Please enter your username: ");
		String inputName = scanner.nextLine();
		System.out.print("Please enter your password: ");
		String inputPass = scanner.nextLine();
		byte[] inputPassByte = inputPass.getBytes();
		boolean result = false;
		try {
			result = userService.authenticateUser(inputName, inputPassByte);
		} catch (Exception e) {

		}
		User user = null;

		if (result) {
			user = userService.getUser(inputName);
			System.out.println("Login Successful! Welcome " + inputName);
		} else {
			System.out.println("Login failed");
		}
		return user;
	}
}
