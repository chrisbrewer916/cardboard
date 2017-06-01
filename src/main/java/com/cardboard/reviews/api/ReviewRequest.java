package com.cardboard.reviews.api;

import java.util.Date;
import java.util.UUID;

import com.cardboard.reviews.dao.ReviewDB;

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

	public ReviewRequest(String user, UUID thingId, int overallRating, String description) {
		this();
		this.userId = ReviewDB.getUser(user);
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
