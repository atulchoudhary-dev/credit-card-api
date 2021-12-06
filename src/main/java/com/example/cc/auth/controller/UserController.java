package com.example.cc.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cc.auth.model.User;
import com.example.cc.auth.service.UserService;

@RestController
public class UserController

{
	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public void signUp(@RequestBody User user) {
		userService.addUser(user);
	}

}
