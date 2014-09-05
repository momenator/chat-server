package com.rafdi.chat.server.model.message;

import java.util.List;

import com.rafdi.chat.server.model.user.User;

public interface MessageFactory {
	public Message createMessage(String message, List<Emoticon> emoticons,
			String userName);

	public Message createMessage(String message, User user)
			throws InvalidMessageException;
}
