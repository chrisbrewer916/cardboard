package com.cardboard.reviews.api;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.cardboard.reviews.model.User;
import com.cardboard.reviews.service.ServiceFactory;

public class ReviewRequest {

	// the ID of this request
	private UUID id;

	// who, what, when...
	private UUID userId;
	private UUID thingId;
	private Date date;

	private int overallRating;
	private String description;

	private ReviewRequest() {
		this.id = UUID.randomUUID();
		this.date = new Date();
	}

	public ReviewRequest(String username, UUID thingId, int overallRating, String description) {
		this();
		
		Optional<User> user = ServiceFactory.getUserService().getUser(username);
		if (user.isPresent()) {
			this.userId = user.get().getId();
		} else {
			throw new WebApplicationException("Could not find user with username: " + username, Response.Status.NOT_FOUND);
		}
		
		this.thingId = thingId;
		this.overallRating = overallRating;
		this.description = description;
	}

	public UUID getId() {
		return id;
	}

	public UUID getUserId() {
		return userId;
	}
	
	public UUID getThingId() {
		return thingId;
	}
	
	public Date getDate() {
		return date;
	}

	public int getOverallRating() {
		return overallRating;
	}

	public String getDescription() {
		return description;
	}
}
