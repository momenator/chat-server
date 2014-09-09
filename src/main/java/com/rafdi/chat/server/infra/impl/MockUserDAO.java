package com.rafdi.chat.server.infra.impl;

import java.util.HashMap;
import java.util.Map;

import com.rafdi.chat.server.infra.UserDAO;
import com.rafdi.chat.server.model.user.User;

public class MockUserDAO implements UserDAO {
	private Map<String, User> userMap = new HashMap<String, User>();

	@Override
	public void save(User user) {
		if (user == null) {
			throw new NullPointerException("User can't be null");
		}
		userMap.put(user.getName(), user);
	}

	@Override
	public User get(Object userName) {
		User user = userMap.get(userName);
		if (user == null) {
			throw new NullPointerException("User " + userName
					+ " is not registered");
		}

		return user;

	}

}
