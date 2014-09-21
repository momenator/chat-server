package com.rafdi.chat.server.rest.resources;

public class UserResource {
	private long userID;
	private String name;
	private String password;

	public UserResource() {

	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public UserResource(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;

	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

}
