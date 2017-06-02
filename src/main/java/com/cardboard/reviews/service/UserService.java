package com.cardboard.reviews.service;

import java.util.Optional;

import com.cardboard.reviews.dao.UserDao;
import com.cardboard.reviews.model.User;

public class UserService {

	UserDao userDao;
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public Optional<User> getUser(String username) {
		return userDao.findByUsername(username);
	}

	public User create(User user) {
		return userDao.create(user);
	}
}
