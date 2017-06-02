package com.cardboard.reviews.api;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.cardboard.reviews.model.User;
import com.cardboard.reviews.service.ServiceFactory;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	public UserResource() {
	}

	@POST
	@Timed
	@UnitOfWork
	public Response postUser(@Context UriInfo uriInfo, User user) {
		User newUser = ServiceFactory.getUserService().create(user);
		return Response.created(uriInfo.getAbsolutePath()).entity(newUser).build();
	}

	@GET
	@Timed
	@UnitOfWork
	public User getReview(@Context UriInfo uriInfo, @QueryParam("username") String username) {
		Optional<User> findUser = ServiceFactory.getUserService().getUser(username);
		if (findUser.isPresent()) {
			return findUser.get();
		} else {
			throw new WebApplicationException("Could not find user with username: " + username,
					Response.Status.NOT_FOUND);
		}
	}
}
