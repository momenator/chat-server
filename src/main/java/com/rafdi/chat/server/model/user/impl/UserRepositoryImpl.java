package com.rafdi.chat.server.model.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rafdi.chat.server.infra.dao.UserDAO;
import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.model.user.UserRepository;

public class UserRepositoryImpl implements UserRepository {
	final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
	private UserDAO dao;

	public UserRepositoryImpl(UserDAO dao) {
		this.dao = dao;
	}

	@Override
	public boolean saveUser(User user) {
		if (user == null) {
			throw new NullPointerException("User can't be null");
		}
		dao.save(user);
		return true;
	}

	@Override
	public User findUserByName(String userName) {
		User user = dao.get(userName);
		if (user == null) {
			throw new NullPointerException("User " + userName
					+ " is not registered");
		}

		return user;
	}
}
