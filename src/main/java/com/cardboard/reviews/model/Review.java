package com.cardboard.reviews.model;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.cardboard.reviews.api.ReviewRequest;

@Entity
@Table(name = "reviews")
@NamedQueries({ 
	@NamedQuery(name = "com.cardboard.reviews.model.Review.findAll", 
			query = "SELECT r FROM Review r") })

public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;

	// who, what, when...
	@Column(name = "userId", nullable = false)
	private UUID userId;
	@Column(name = "thingId", nullable = false)
	private UUID thingId;
	@Column(name = "reviewDate", nullable = false)
	private Date reviewDate;
	@Column(name = "overallRating", nullable = false)
	private int overallRating;

	@Column(name = "description", nullable = true)
	private String description;
	@ElementCollection
	private Map<String, Integer> otherRatings;

	public Review(UUID userId, Date date, UUID thingId, int overallRating, String description,
			Map<String, Integer> otherRatings) {
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
		this(reviewRequest.getUserId(), reviewRequest.getDate(), reviewRequest.getThingId(),
				reviewRequest.getOverallRating(), reviewRequest.getDescription(),
				reviewRequest.getOtherRatings());
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

	public String getDescription() {
		return description;
	}

	public Map<String, Integer> getOtherRatings() {
		return otherRatings;
	}
}
