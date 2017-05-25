package com.cardboard.reviews.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.SessionFactory;

import com.cardboard.reviews.model.Review;

import io.dropwizard.hibernate.AbstractDAO;

public class ReviewDao extends AbstractDAO<Review> {

	public ReviewDao(SessionFactory factory) {
		super(factory);
	}
	
	public Optional<Review> findById(UUID id) {
        return Optional.ofNullable(get(id));
    }

    public Review create(Review review) {
        return persist(review);
    }

    public List<Review> findAll() {
        return list(namedQuery("com.cardboard.reviews.model.Review.findAll"));
    }

}
