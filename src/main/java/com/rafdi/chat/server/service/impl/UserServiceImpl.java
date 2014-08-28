package com.rafdi.chat.server.service.impl;

import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.model.user.UserFactory;
import com.rafdi.chat.server.model.user.UserRepository;
import com.rafdi.chat.server.model.user.impl.UserFactoryImpl;
import com.rafdi.chat.server.model.user.impl.UserRepositoryImpl;
import com.rafdi.chat.server.service.UserService;

public class UserServiceImpl implements UserService {

	private UserFactory userFactory;
	private UserRepository userRepository;

	public UserServiceImpl() {
		super();
		this.userFactory = new UserFactoryImpl();
		this.userRepository = new UserRepositoryImpl();
	}

	@Override
	public boolean verifyUser(String name, byte[] password) {
		User user = userRepository.findUserByName(name);
		if (user != null) {
			String userPass = new String(user.getPassword());
			String inputPass = new String(password);
			if (userPass.equals(inputPass)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public User createUser(String name, byte[] password) {
		User user = userFactory.createUser(name, password);
		userRepository.saveUser(user);
		return user;
	}

}
