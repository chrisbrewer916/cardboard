package com.cardboard.reviews.api;

import java.util.Collection;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.cardboard.reviews.dao.ReviewDB;
import com.cardboard.reviews.model.Review;
import com.cardboard.reviews.model.ReviewSummary;
import com.codahale.metrics.annotation.Timed;

@Path("/things")
@Produces(MediaType.APPLICATION_JSON)
public class ThingResource {

	@POST
	@Timed
	@Path("/{thingId}/overallratings/{rating}")
	public Response postReview(@Context UriInfo uriInfo, @HeaderParam("user") String user, @PathParam("thingId") UUID thingId,
			@PathParam("rating") int rating) {
		
		ReviewRequest request = new ReviewRequest(user, thingId, rating, null, null);
		Collection<Review> reviews = ReviewDB.addReview(new Review(request));
		
		return Response.created(uriInfo.getAbsolutePath()).entity(new ReviewSummary(reviews)).build();
	}
	
	@GET
	@Path("/{id}/reviews")
	@Timed
	public ReviewSummary getReview(@PathParam("id") UUID id) {
		return new ReviewSummary(ReviewDB.getReviews(id));
	}
}
