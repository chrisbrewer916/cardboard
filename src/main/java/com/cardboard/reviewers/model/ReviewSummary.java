package com.cardboard.reviewers.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class ReviewSummary {

	private Optional<Collection<Review>> reviews;

	public ReviewSummary(Collection<Review> reviews) {
		this.reviews = Optional.of(reviews);
	}

	public Optional<Collection<Review>> getReviews() {
		return reviews;
	}

	public void setReviews(Collection<Review> reviews) {
		this.reviews = Optional.of(reviews);
	}
	
	public boolean addReview(Review review) {
		if (reviews.isPresent()) {
			return reviews.get().add(review);
		}
		setReviews(Arrays.asList(review));
		return true;
	}

	public int getSum() {
		if (reviews.isPresent()) {
			return reviews.get().stream().mapToInt(r -> r.getOverallRating()).sum();
		}
		return 0;
	}

}
