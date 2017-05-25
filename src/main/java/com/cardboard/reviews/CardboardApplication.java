package com.cardboard.reviews;

import com.cardboard.reviews.api.ReviewResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CardboardApplication extends Application<CardboardConfiguration> {
	public static void main(String[] args) throws Exception {
		new CardboardApplication().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<CardboardConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(CardboardConfiguration configuration, Environment environment) {
		final ReviewResource reviewResource = new ReviewResource();
		
//		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
//		environment.healthChecks().register("template", healthCheck);

		environment.jersey().register(reviewResource);
	}
}
