package com.rafdi.chat.model.user.impl;

import java.util.HashMap;
import java.util.Map;

import com.rafdi.chat.model.user.User;
import com.rafdi.chat.model.user.UserRepository;

public class UserRespositoryImpl implements UserRepository {
	Map<String, User> userMap = new HashMap<String, User>();

	@Override
	public boolean saveUser(User user) {
		userMap.put(user.getName(), user);
		return true;
	}
}
