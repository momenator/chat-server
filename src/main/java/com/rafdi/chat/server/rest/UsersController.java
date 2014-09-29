package com.rafdi.chat.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rafdi.chat.server.model.user.User;
import com.rafdi.chat.server.rest.resources.UserResource;
import com.rafdi.chat.server.service.UserService;

@RestController
public class UsersController {
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User createUser(@RequestBody UserResource user) {
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		return userService.createUser(user.getName(), user.getPassword()
				.getBytes());

	}

}
