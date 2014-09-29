package com.rafdi.chat.server.model.message;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.rafdi.chat.server.model.user.User;

public class Message {
	private String message;
	private List<Emoticon> emoticons;
	private Date timestamp;
	private User user;

	@Override
	public String toString() {
		return "Message [message=" + message + ", timestamp=" + timestamp + "]";
	}

	public Message() {
	}

	public Message(String message, User user) throws InvalidMessageException {
		if (message.length() > 250) {
			throw new InvalidMessageException(
					"message can't be longer than 250 chars!");
		}
		if (user == null) {
			throw new InvalidMessageException("User can't be null!");
		}
		this.message = message;
		this.user = user;
		this.timestamp = Calendar.getInstance().getTime();
	}

	public String getMessage() {
		return message;
	}

	public List<Emoticon> getEmoticons() {
		return emoticons;
	}

	public void setEmoticons(List<Emoticon> emoticons) {
		this.emoticons = emoticons;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
