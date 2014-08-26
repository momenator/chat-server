package com.rafdi.chat.model.user;

public class InvalidNameException extends RuntimeException {
	public InvalidNameException(String message) {
		super(message);
	}
}
