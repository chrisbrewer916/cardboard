package com.cardboard.reviewers.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cardboard.reviewers.model.Review;
import com.cardboard.reviewers.model.ReviewSummary;
import com.codahale.metrics.annotation.Timed;

@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
public class ReviewResource {

	private static Collection<Review> reviews = new ArrayList<>();
	
    public ReviewResource() {
    }
    
    @POST
    @Timed
    public Response postReview(Optional<Review_Request> request) {
    	if (request.isPresent()) {
    		reviews.add(new Review(request.get()));
    	}
    	URI location = URI.create("reviews/" + request.get().getId());
    	return Response.created(location).entity(new ReviewSummary(reviews)).build();
//    	return new ReviewSummary(reviews);
    }
    
    @GET
    @Timed
    public ReviewSummary getReviews() {
    	return new ReviewSummary(reviews);
    }
    
}