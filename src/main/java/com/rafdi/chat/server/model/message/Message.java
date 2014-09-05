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

	public Message(String message, User user) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((emoticons == null) ? 0 : emoticons.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (emoticons == null) {
			if (other.emoticons != null)
				return false;
		} else if (!emoticons.equals(other.emoticons))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
