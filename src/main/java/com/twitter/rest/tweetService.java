/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twitter.rest;

import com.twitter.TwitterCrawler;
import com.twitter.model.TwitterApiResponse;
import java.net.URISyntaxException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * web service with path /getCountTweets to retrieve the data of twitter REST API periodically
 * return the TwitterApiResponse resource as JSON
 * @author lenaki_7
 */
@Path("getCountTweets")
public class tweetService {

    /**
     * Creates a new instance of DummyResource
     * @throws java.net.URISyntaxException
     */
    public tweetService() throws URISyntaxException {
    }

    /**
     *
     * @param hashtags
     * @param interval
     * @param sinceID
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TwitterApiResponse getTotalNumTweets(@QueryParam(value = "hashtag") String hashtags, @QueryParam(value = "interval") String interval,
            @QueryParam(value = "sinceID") String sinceID) {
        //************************ Variables *************************
        TwitterApiResponse response = new TwitterApiResponse();
        TwitterCrawler crawler;
        //************************ Action *************************
        try {
            crawler = new TwitterCrawler(hashtags, Integer.parseInt(interval), new Long(sinceID));
            response = crawler.stream();
        } catch (Exception e) {

        }

        return response;

    }
}// end class
