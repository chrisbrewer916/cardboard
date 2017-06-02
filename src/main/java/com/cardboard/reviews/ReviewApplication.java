package com.cardboard.reviews;

import com.cardboard.reviews.api.ReviewResource;
import com.cardboard.reviews.api.ThingResource;
import com.cardboard.reviews.api.UserResource;
import com.cardboard.reviews.dao.ReviewDao;
import com.cardboard.reviews.dao.ThingDao;
import com.cardboard.reviews.dao.UserDao;
import com.cardboard.reviews.model.Review;
import com.cardboard.reviews.model.Thing;
import com.cardboard.reviews.model.User;
import com.cardboard.reviews.service.ServiceFactory;
import com.cardboard.reviews.service.UserService;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
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
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
        bootstrap.addBundle(new MigrationsBundle<ReviewConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ReviewConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
	}

	@Override
	public void run(ReviewConfiguration configuration, Environment environment) {
		
		final ReviewDao reviewDao = new ReviewDao(hibernateBundle.getSessionFactory());
		final ThingDao thingDao = new ThingDao(hibernateBundle.getSessionFactory());
		final UserDao userDao = new UserDao(hibernateBundle.getSessionFactory());
		
		final UserService userService = new UserService(userDao);
		ServiceFactory.setUserService(userService);
		
		final ReviewResource reviewResource = new ReviewResource(reviewDao);
		final ThingResource thingResource = new ThingResource(thingDao, reviewDao);
		final UserResource userResource = new UserResource();
		
//		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
//		environment.healthChecks().register("template", healthCheck);

		environment.jersey().register(reviewResource);
		environment.jersey().register(thingResource);
		environment.jersey().register(userResource);
	}
}
