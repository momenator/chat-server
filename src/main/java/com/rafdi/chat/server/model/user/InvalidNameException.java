package com.rafdi.chat.server.model.user;

public class InvalidNameException extends RuntimeException {
	public InvalidNameException(String message) {
		super(message);
	}
}
