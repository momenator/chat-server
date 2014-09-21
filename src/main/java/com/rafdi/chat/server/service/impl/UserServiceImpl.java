package com.rafdi.chat.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.model.user.UserFactory;
import com.rafdi.chat.server.model.user.UserRepository;
import com.rafdi.chat.server.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	private UserFactory userFactory;
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserFactory userFactory,
			UserRepository userRepository) {
		this.userFactory = userFactory;
		this.userRepository = userRepository;
	}

	@Override
	public boolean authenticateUser(String name, byte[] password) {
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

	@Override
	public User getUser(String inputName) {
		User user = userRepository.findUserByName(inputName);
		return user;
	}
}
