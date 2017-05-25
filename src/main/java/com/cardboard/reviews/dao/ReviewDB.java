package com.cardboard.reviews.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.NotFoundException;

import com.cardboard.reviews.model.Review;

public class ReviewDB {

	private static Map<UUID, Collection<Review>> reviews = new HashMap<>();
	private static Map<UUID, String> things = new HashMap<>();
	private static Map<UUID, String> users = new HashMap<>();
	
	public static Collection<Review> getReviews() {
		Collection<Review> allReviews = new ArrayList<>();
		for (Collection<Review> someReviews : reviews.values()) {
			allReviews.addAll(someReviews);
		}
		return allReviews;
	}
	
	public static Collection<Review> getReviews(UUID thingId) {
		if (!reviews.containsKey(thingId)) {
			throw new NotFoundException("Could not find thing id:" + thingId);
		}
		return reviews.get(thingId);
	}
	
	public static Collection<Review> addReview(Review review) {
		Collection<Review> thingReviews = getReviews(review.getThingId());
		thingReviews.add(review);
		return thingReviews;
	}
	
	public static Collection<Review> getReviewsForUser(UUID userId, UUID thingId) {
		Collection<Review> thingReviews = getReviews(thingId);
		Collection<Review> userReviews = new ArrayList<>();
		for (Review review : thingReviews) {
			if (userId.equals(review.getUserId())) {
				userReviews.add(review);
			}
		}
		return userReviews;
	}
	
	public static String getThing(UUID thingId) {
		return things.get(thingId);
	}
	
	public static String addThing(UUID id, String name) {
		return things.put(id, name);
	}
	
	public static String getUser(UUID userId) {
		return users.get(userId);
	}
	
	public static UUID getUser(String userName) {
		for (UUID id : users.keySet()) {
			if (userName.equalsIgnoreCase(users.get(id))) {
				return id;
			}
		}
		throw new NotFoundException("Could not find user:" + userName);
	}
}
