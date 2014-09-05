package com.rafdi.chat.server.model.message;

public class InvalidMessageException extends Exception {
	public InvalidMessageException(String message) {
		super(message);
	}
}
