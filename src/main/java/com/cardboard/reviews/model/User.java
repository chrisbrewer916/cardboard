package com.cardboard.reviews.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NamedQueries( {@NamedQuery(
					name = "com.cardboard.reviews.model.User.findAll", 
					query = "SELECT u FROM User u"),
				@NamedQuery(
						name="com.cardboard.reviews.model.User.findByUsername",
						query="SELECT u FROM User u WHERE u.username LIKE :username"
			)})
public class User {

	@Id
	private UUID id;

	@Column(name = "username", nullable = false)
	private String username;

	public User() {
		this.id = UUID.randomUUID();
	}
	
	public User(String username) {	
		this.username = username;
	}

	public UUID getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

}
