package com.example.cc.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cc.auth.exception.UserAlreadyExistsException;
import com.example.cc.auth.model.User;
import com.example.cc.auth.repository.UserRepository;
import com.example.cc.auth.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	public User addUser(User user) {
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new UserAlreadyExistsException("User already exits !");
		}
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

}
