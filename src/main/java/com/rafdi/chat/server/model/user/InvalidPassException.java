package com.rafdi.chat.server.model.user;

public class InvalidPassException extends RuntimeException {
	public InvalidPassException(String message) {
		super(message);
	}
}
