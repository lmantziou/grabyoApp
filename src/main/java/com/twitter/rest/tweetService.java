/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twitter.rest;

import com.twitter.TwitterClient;
import com.twitter.TwitterCrawler;
import com.twitter.model.TwitterApiResponse;
import java.net.URISyntaxException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;

/**
 *
 * @author lenaki_7
 */
@Path("getCountTweets")
public class tweetService {

    /**
     * Creates a new instance of DummyResource
     *
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
//        String[] keywords = {"#championsleague", "#ManchesterUnited"};
//        String[] keywords = {"#EU60", "#SurvivorGR"};
        String[] keywords = {};

        TwitterCrawler crawler;
        int total = 0;
        //************************ Action *************************
        try {

            if (hashtags.length() > 1) {
                keywords = hashtags.split(",");
            }
            System.out.println("kyword " + hashtags);
//            crawler = new TwitterCrawler(keywords, Integer.parseInt(interval));

            crawler = new TwitterCrawler(hashtags, Integer.parseInt(interval),new Long(sinceID));

//            total = crawler.stream();
            response = crawler.stream();
        } catch (Exception e) {

        }

//        return total;
return response;

    }

    public static void main(String args[]) throws TwitterException, InterruptedException {
        TwitterApiResponse response = new TwitterApiResponse();
//        TwitterClient twitterClient = new TwitterClient();
//        Configuration conf = twitterClient.getConfiguration();
//        String[] keywords = {"#championsleague", "#ManchesterUnited"};
//        String[] keywords = {"#EU60", "#SurvivorGR"};

// long timeNow = System.currentTimeMillis();
//        long afterOneMin = timeNow + (10 * 1000);
//        
//        System.out.println("now "+timeNow+" after "+afterOneMin);
//        String keywords = "#EU60,#SurvivorGR";
        String keywords = "#SurvivorGR";
//        TwitterCrawler crawler = new TwitterCrawler(keywords, 10,new Long("846706858488451072"));
        TwitterCrawler crawler = new TwitterCrawler(keywords, 10, 0);
        response = crawler.stream();
        System.out.println("total " + response.getCount());

    }//end main
}//
