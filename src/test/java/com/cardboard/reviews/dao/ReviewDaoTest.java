package com.cardboard.reviews.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.cardboard.reviews.model.Review;

import io.dropwizard.testing.junit.DAOTestRule;


public class ReviewDaoTest {

	@Rule
	public DAOTestRule daoTestRule = DAOTestRule.newBuilder()
	.addEntityClass(Review.class)
	.setShowSql(true)
//	.useSqlComments(true)
	.build();
	
	private ReviewDao reviewDao;
	
	@Before
	public void setUp() throws Exception {
		reviewDao = new ReviewDao(daoTestRule.getSessionFactory());
	}
	
	@Test
	public void createReview() {
		Review review = daoTestRule.inTransaction(() -> reviewDao.create(new Review(UUID.randomUUID(), new Date(), UUID.randomUUID(), 2, null)));
		assertTrue(review.getId() != null);
		Review findReview = reviewDao.findById(review.getId()).get();
		assertNotNull(findReview);
		assertEquals(findReview, review);
	}
}
