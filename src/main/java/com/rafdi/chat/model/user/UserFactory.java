package com.rafdi.chat.model.user;

public interface UserFactory {

	User createUser(String name) throws InvalidNameException;

}
