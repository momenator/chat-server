package com.rafdi.chat.server.model.user;

public interface UserFactory {

	User createUser(String name, byte[] password) throws InvalidNameException;

}
