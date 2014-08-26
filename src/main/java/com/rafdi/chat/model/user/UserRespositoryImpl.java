package com.rafdi.chat.model.user;

import java.util.HashMap;
import java.util.Map;

public class UserRespositoryImpl implements UserRepository {
	Map<String, User> userMap = new HashMap<String, User>();

	@Override
	public boolean saveUser(User user) {
		userMap.put(user.getName(), user);
		return true;
	}
}