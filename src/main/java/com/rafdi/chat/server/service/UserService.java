package com.rafdi.chat.server.service;

import com.rafdi.chat.server.model.user.User;

public interface UserService {
	public User createUser(String name, byte[] password);

	public boolean verifyUser(String name, byte[] password);

}
