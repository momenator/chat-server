package com.rafdi.chat.server.model.message.impl;

import java.util.List;

import com.rafdi.chat.server.model.message.Emoticon;
import com.rafdi.chat.server.model.message.InvalidMessageException;
import com.rafdi.chat.server.model.message.Message;
import com.rafdi.chat.server.model.message.MessageFactory;
import com.rafdi.chat.server.model.user.User;

public class MessageFactoryImpl implements MessageFactory {

	@Override
	public Message createMessage(String message, List<Emoticon> emoticons,
			String userName) {
		throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
	}

	@Override
	public Message createMessage(String message, User user)
			throws InvalidMessageException {
		if (message == null) {
			throw new InvalidMessageException("message can't be null");
		}
		Message incomingMessage = new Message(message, user);
		return incomingMessage;
	}

}
