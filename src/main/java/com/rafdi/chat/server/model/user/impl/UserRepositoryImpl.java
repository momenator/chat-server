package com.rafdi.chat.server.model.user.impl;

import java.util.HashMap;
import java.util.Map;

import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.model.user.UserRepository;

public class UserRepositoryImpl implements UserRepository {
	private Map<String, User> userMap = new HashMap<String, User>();

	@Override
	public boolean saveUser(User user) {
		if (user == null) {
			throw new NullPointerException("User can't be null");
		}
		userMap.put(user.getName(), user);
		return true;
	}

	@Override
	public User findUserByName(String name) {
		User user = userMap.get(name);
		if (user == null) {
			throw new NullPointerException("User " + name
					+ " is not registered");
		}

		return user;

	}
}
