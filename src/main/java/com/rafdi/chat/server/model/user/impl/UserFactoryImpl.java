package com.rafdi.chat.server.model.user.impl;

import com.rafdi.chat.server.model.user.InvalidNameException;
import com.rafdi.chat.server.model.user.InvalidPassException;
import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.model.user.UserFactory;

public class UserFactoryImpl implements UserFactory {

	@Override
	public User createUser(String name, byte[] password)
			throws InvalidNameException {
		validateName(name);
		validatePass(password);
		User user = new User(name, password);
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
		} else if (name.length() > 20) {
			throw new InvalidNameException("name can't be longer than 20 chars");
		}

		return validity;
	}

	private boolean validatePass(byte[] pass) throws InvalidPassException {
		boolean validity = true;
		if (pass == null) {
			throw new InvalidPassException("password can't be null");
		} else if (new String(pass).length() < 6) {
			throw new InvalidPassException("password must be 6 chars or longer");
		} else if (!hasAtLeast2Numbers(new String(pass))) {

			throw new InvalidPassException(
					"password must contain at least one number!");
		}

		return validity;
	}

	private boolean hasAtLeast2Numbers(String password) {
		int numCount = 0;
		String numbers = "01233456789";
		char[] letters = password.toCharArray();
		for (char c : letters) {
			if (numbers.indexOf(c) > -1) {
				numCount++;
			}
		}
		if (numCount >= 2) {
			return true;
		} else {
			return false;
		}
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
