package com.cardboard.reviewers.api;

import java.util.Map;
import java.util.UUID;

public class Review_Request {

	private UUID id;

	private UUID thingId;
	private String url;

	private int overallRating;
	private Map<String, Integer> ratings;

	@SuppressWarnings("unused")
	private Review_Request() {
	}

	public Review_Request(UUID thingId, String url, int overallRating, Map<String, Integer> ratings) {
		this.id = UUID.randomUUID();
		this.thingId = thingId;
		this.url = url;
		this.overallRating = overallRating;
		this.ratings = ratings;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
