package com.cardboard.reviews;

import com.cardboard.reviews.api.ReviewResource;
import com.cardboard.reviews.api.ThingResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ReviewApplication extends Application<ReviewConfiguration> {
	public static void main(String[] args) throws Exception {
		new ReviewApplication().run(args);
	}

	@Override
	public String getName() {
		return "reviews";
	}

	@Override
	public void initialize(Bootstrap<ReviewConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(ReviewConfiguration configuration, Environment environment) {
		final ReviewResource reviewResource = new ReviewResource();
		final ThingResource thingResource = new ThingResource();
		
//		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
//		environment.healthChecks().register("template", healthCheck);

		environment.jersey().register(reviewResource);
		environment.jersey().register(thingResource);
	}
}
