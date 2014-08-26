package com.rafdi.chat.model.user.impl;

import com.rafdi.chat.model.user.InvalidNameException;
import com.rafdi.chat.model.user.User;
import com.rafdi.chat.model.user.UserFactory;

public class UserFactoryImpl implements UserFactory {

	@Override
	public User createUser(String name) throws InvalidNameException {
		validateName(name);
		User user = new User(name);
		return user;
	}

	private boolean validateName(String name) throws InvalidNameException {
		boolean validity = true;
		if (name == null) {
			throw new InvalidNameException("name can't be null");
		} else if (name.indexOf(' ') > -1) {
			throw new InvalidNameException("name can't contain spaces");
		} else if (name.length() == 0) {
			throw new InvalidNameException("name can't be an empty string");
		} else if (!isAlphanumeric(name)) {
			throw new InvalidNameException("name must be alphanumeric chars");
		}

		return validity;
	}

	private boolean isAlphanumeric(String name) {
		char[] letters = name.toCharArray();
		for (char c : letters) {
			if (!Character.isLetterOrDigit(c)) {
				return false;
			}
		}
		return true;
	}

}
