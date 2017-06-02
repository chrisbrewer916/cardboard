package com.cardboard.reviews.api;

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

import com.cardboard.reviews.dao.ReviewDao;
import com.cardboard.reviews.dao.ThingDao;
import com.cardboard.reviews.model.Review;
import com.cardboard.reviews.model.ReviewSummary;
import com.cardboard.reviews.model.Thing;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/things")
@Produces(MediaType.APPLICATION_JSON)
public class ThingResource {
	
	private ThingDao thingDao;
	private ReviewDao reviewDao;
	
	public ThingResource(ThingDao thingDao, ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
		this.thingDao = thingDao;
	}

	@POST
	@Timed
	@Path("/{thingId}/overallratings/{rating}")
	@UnitOfWork
	public Response postReview(@Context UriInfo uriInfo, @HeaderParam("user") String user, @PathParam("thingId") UUID thingId,
			@PathParam("rating") int rating) {
		
		ReviewRequest request = new ReviewRequest(user, thingId, rating, null);
		Review Review = reviewDao.create(new Review(request));
		
		return Response.created(uriInfo.getAbsolutePath()).entity(Review).build();
	}
	
	@POST
	@Timed
	@UnitOfWork
	public Response postUser(@Context UriInfo uriInfo, Thing thing) {
		Thing newThing = thingDao.create(thing);
		return Response.created(uriInfo.getAbsolutePath()).entity(newThing).build();
	}
	
	@GET
	@Path("/{id}/reviews")
	@Timed
	@UnitOfWork
	public ReviewSummary getReview(@PathParam("id") UUID id) {
		return new ReviewSummary(reviewDao.findAll());
	}
}
