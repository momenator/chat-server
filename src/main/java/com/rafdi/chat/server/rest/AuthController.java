package com.rafdi.chat.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rafdi.chat.server.rest.resources.UserResource;
import com.rafdi.chat.server.service.UserService;

@RestController
public class AuthController {
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public boolean auth(@RequestBody UserResource user) {
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		return userService.authenticateUser(user.getName(), user.getPassword()
				.getBytes());

	}
}
