package com.cardboard.reviews.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.SessionFactory;

import com.cardboard.reviews.model.User;

import io.dropwizard.hibernate.AbstractDAO;

public class UserDao extends AbstractDAO<User> {

	public UserDao(SessionFactory factory) {
		super(factory);
	}

	public Optional<User> findById(UUID id) {
		return Optional.ofNullable(get(id));
	}

	@SuppressWarnings("unchecked")
	public Optional<User> findByUsername(String username) {
		return Optional.ofNullable(
				uniqueResult(namedQuery("com.cardboard.reviews.model.User.findByUsername").setParameter(0, username)));
	}

	public User create(User user) {
		return persist(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return list(namedQuery("com.cardboard.reviews.model.User.findAll"));
	}

}
