package com.cardboard.reviewers.model;

import java.util.Map;
import java.util.UUID;

import com.cardboard.reviewers.api.Review_Request;

public class Review {

	private UUID id;

	private UUID thingId;
	private int overallRating;
	private Map<String, Integer> ratings;

	public Review(UUID thingId, int overallRating, Map<String, Integer> ratings) {
		super();
		this.id = UUID.randomUUID();
		this.thingId = thingId;
		this.overallRating = overallRating;
		this.ratings = ratings;
	}

	public Review(Review_Request review_Request) {
		this(review_Request.getThingId(), review_Request.getOverallRating(), review_Request.getRatings());
	}

	public UUID getId() {
		return id;
	}

	public UUID getThingId() {
		return thingId;
	}

	public void setThingId(UUID thingId) {
		this.thingId = thingId;
	}

	public int getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(int overallRating) {
		this.overallRating = overallRating;
	}

	public Map<String, Integer> getRatings() {
		return ratings;
	}

	public void setRatings(Map<String, Integer> ratings) {
		this.ratings = ratings;
	}
}
