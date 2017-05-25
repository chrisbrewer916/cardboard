package com.cardboard.reviews.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.cardboard.reviews.model.Review;
import com.cardboard.reviews.model.ReviewSummary;
import com.codahale.metrics.annotation.Timed;

@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
public class ReviewResource {

	private static Collection<Review> reviews = new ArrayList<>();

	public ReviewResource() {
	}

	@POST
	@Timed
	public Response postReview(Optional<ReviewRequest> request) {
		if (request.isPresent()) {
			reviews.add(new Review(request.get()));
		}
		URI location = URI.create("reviews/" + request.get().getId());
		return Response.created(location).entity(new ReviewSummary(reviews)).build();
	}

	@POST
	@Timed
	@Path("/{reviewId}/overallratings/{rating}")
	public Response postReview(@Context UriInfo uriInfo, @HeaderParam("user") String user, @PathParam("reviewId") UUID reviewId,
			@PathParam("rating") int rating) {
		UUID thingId = null;
		for (Review review : reviews) {
			if (review.getId().equals(reviewId)) {
				thingId = review.getThingId();
				break;
			}
		}
		if (thingId == null) {
			throw new WebApplicationException("Could not find review id:" + reviewId, Status.NOT_FOUND);
		}
		
		ReviewRequest request = new ReviewRequest(user, thingId, rating, null, null);
		reviews.add(new Review(request));
		
		return Response.created(uriInfo.getAbsolutePath()).entity(new ReviewSummary(reviews)).build();
	}

	@GET
	@Timed
	public ReviewSummary getReviews() {
		return new ReviewSummary(reviews);
	}

	@GET
	@Path("/{id}")
	@Timed
	public Review getReview(@PathParam("id") UUID id) {
		for (Review review : reviews) {
			if (review.getId().equals(id)) {
				return review;
			}
		}
		throw new WebApplicationException("Could not find id:" + id, Status.NOT_FOUND);
	}

}