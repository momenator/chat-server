package com.rafdi.chat.server.model.user;

public interface UserRepository {

	boolean saveUser(User user);

	User findUserByName(String name);

}
