package com.cardboard.reviews.api;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cardboard.reviews.dao.ReviewDB;
import com.cardboard.reviews.model.Review;
import com.cardboard.reviews.model.ReviewSummary;
import com.codahale.metrics.annotation.Timed;

@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
public class ReviewResource {

	public ReviewResource() {
	}

	@POST
	@Timed
	public Response postReview(Optional<ReviewRequest> request) {
		Collection<Review> reviews = null;
		if (request.isPresent()) {
			reviews = ReviewDB.addReview(new Review(request.get()));
		}
		URI location = URI.create("reviews/" + request.get().getId());
		return Response.created(location).entity(new ReviewSummary(reviews)).build();
	}

	@GET
	@Timed
	public ReviewSummary getReviews() {
		return new ReviewSummary(ReviewDB.getReviews());
	}
}