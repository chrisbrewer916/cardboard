package com.cardboard.reviews.api;

import java.net.URI;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cardboard.reviews.dao.ReviewDao;
import com.cardboard.reviews.model.Review;
import com.cardboard.reviews.model.ReviewSummary;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
public class ReviewResource {

	private ReviewDao reviewDao;
	
	public ReviewResource(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}

	@POST
	@Timed
	@UnitOfWork
	public Response postReview(Optional<ReviewRequest> request) {
		Review review = null;
		if (request.isPresent()) {
			review = reviewDao.create(new Review(request.get()));
		}
		URI location = URI.create("reviews/" + request.get().getId());
		return Response.created(location).entity(review).build();
	}

	@GET
	@Timed
	@UnitOfWork
	public ReviewSummary getReviews() {
		return new ReviewSummary(reviewDao.findAll());
	}
}