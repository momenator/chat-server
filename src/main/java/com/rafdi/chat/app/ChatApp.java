package com.rafdi.chat.app;

import java.util.Scanner;

import com.rafdi.chat.model.user.User;
import com.rafdi.chat.model.user.UserFactoryImpl;

public class ChatApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		UserFactoryImpl test = new UserFactoryImpl();
		System.out.println("---WELCOME TO CHATAPP---");
		System.out.print("Please enter your name: ");
		String name = scanner.nextLine();
		User user = test.createUser(name);
		System.out.println("Hello " + user.getName() + "!");

	}
}
