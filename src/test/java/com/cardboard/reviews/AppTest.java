package com.cardboard.reviews;

import java.util.UUID;

import com.cardboard.reviews.api.ReviewRequest;
import com.cardboard.reviews.dao.ReviewDB;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws JsonProcessingException 
     */
    public void testApp() throws JsonProcessingException
    {
//    	ReviewDB.addUser(UUID.randomUUID(), "chris");
//    	ObjectMapper mapper = new ObjectMapper();
//    	ReviewRequest r = new ReviewRequest("chris", UUID.randomUUID(), 2, "cool", null);
//    	System.out.println(mapper.writeValueAsString(r));
        assertTrue( true );
    }
}
