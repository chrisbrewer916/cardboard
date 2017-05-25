package com.cardboard.reviews.model;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.cardboard.reviews.api.ReviewRequest;

public class Review {

	private UUID id;

	// who, what, when...
	private UUID userId;
	private UUID thingId;
	private Date reviewDate;
	
	private int overallRating;
	
	private Optional<String> description;
	private Optional<Map<String, Integer>> otherRatings;

	public Review(UUID userId, Date date, UUID thingId, int overallRating, Optional<String> description, Optional<Map<String, Integer>> otherRatings) {
		super();
		this.id = UUID.randomUUID();
		this.userId = userId;
		this.reviewDate = date;
		this.thingId = thingId;
		this.overallRating = overallRating;
		this.description = description;
		this.otherRatings = otherRatings;
	}

	public Review(ReviewRequest reviewRequest) {		
		this(reviewRequest.getUserId(), reviewRequest.getDate(), reviewRequest.getThingId(), reviewRequest.getOverallRating(), Optional.ofNullable(reviewRequest.getDescription()), Optional.ofNullable(reviewRequest.getOtherRatings()));
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
	
	public Date getReveiewDate() {
		return reviewDate;
	}

	public int getOverallRating() {
		return overallRating;
	}

	public Optional<String> getDescription() {
		return description;
	}

	public Optional<Map<String, Integer>> getOtherRatings() {
		return otherRatings;
	}
}
