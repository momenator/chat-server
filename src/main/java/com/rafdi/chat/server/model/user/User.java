package com.rafdi.chat.server.model.user;

public class User {

	private String name;
	private byte[] password;

	public byte[] getPassword() {
		return password;
	}

	public User(String name, byte[] password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
}
