package com.cardboard.reviews;

import com.cardboard.reviews.api.ReviewResource;
import com.cardboard.reviews.api.ThingResource;
import com.cardboard.reviews.dao.ReviewDao;
import com.cardboard.reviews.model.Review;
import com.cardboard.reviews.model.Thing;
import com.cardboard.reviews.model.User;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ReviewApplication extends Application<ReviewConfiguration> {
	
	public static void main(String[] args) throws Exception {
		new ReviewApplication().run(args);
	}
	
	private final HibernateBundle<ReviewConfiguration> hibernateBundle =
	        new HibernateBundle<ReviewConfiguration>(Review.class, Thing.class, User.class) {
	            @Override
	            public DataSourceFactory getDataSourceFactory(ReviewConfiguration configuration) {
	                return configuration.getDataSourceFactory();
	            }
	        };

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
		
		final ReviewDao reviewDao = new ReviewDao(hibernateBundle.getSessionFactory());
		
		final ReviewResource reviewResource = new ReviewResource(reviewDao);
		final ThingResource thingResource = new ThingResource(reviewDao);
		final UserResource userResource = new UserResource();
		
//		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
//		environment.healthChecks().register("template", healthCheck);

		environment.jersey().register(reviewResource);
		environment.jersey().register(thingResource);
	}
}
