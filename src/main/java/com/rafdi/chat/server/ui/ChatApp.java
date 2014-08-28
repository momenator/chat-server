package com.rafdi.chat.server.ui;

import java.util.Scanner;

import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.service.impl.UserServiceImpl;

public class ChatApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		UserServiceImpl test = new UserServiceImpl();
		System.out.println("---WELCOME TO CHATAPP---");
		System.out.println("Please register");
		System.out.print("Please enter your name: ");
		String name = scanner.nextLine();
		System.out.print("Please enter your pass: ");
		String password = scanner.nextLine();
		byte[] passwordByte = password.getBytes();
		User user = test.createUser(name, passwordByte);
		System.out.println("User " + user.getName() + " created successfully");

		System.out.println("-----");
		System.out.println("-----");
		System.out.println("-----");
		System.out.println("Login");
		System.out.print("Please enter your username: ");
		String inputName = scanner.nextLine();
		System.out.print("Please enter your password: ");
		String inputPass = scanner.nextLine();
		byte[] inputPassByte = inputPass.getBytes();
		boolean result = test.verifyUser(inputName, inputPassByte);
		if (result) {
			System.out.println("Login Successful! Welcome " + inputName);
		} else {
			System.out.println("Login failed");
		}
	}
}
