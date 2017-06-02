package com.cardboard.reviews.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "things")
@NamedQueries({ @NamedQuery(name = "com.cardboard.reviews.model.Thing.findAll", query = "SELECT t FROM Thing t") })
public class Thing {

	@Id
	private UUID id;

	@Column(name = "name", nullable = false)
	private String name;

	public Thing() {
		this.id = UUID.randomUUID();
	}
	
	public Thing(String name) {
		this();
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
