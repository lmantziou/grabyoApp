/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twitter.rest;

import com.twitter.TwitterClient;
import com.twitter.TwitterCrawler;
import java.net.URISyntaxException;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
     * @throws java.net.URISyntaxException
     */
    public tweetService() throws URISyntaxException {
    }

    /**
     *
     * @param hashtags
     * @return
     */
    @GET
    @Path("/gett")
    @Produces("application/json")
    public int getTotalNumTweets(@QueryParam(value = "hashtag") String hashtags) {
        //************************ Variables *************************
        TwitterClient twitterClient = new TwitterClient();
        Configuration conf = twitterClient.getConfiguration();

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
            System.out.println("kyword "+hashtags);
            crawler = new TwitterCrawler(keywords, conf);

            total = crawler.stream();
        } catch (Exception e) {

        }

        return total;

    }

    public static void main(String args[]) throws TwitterException, InterruptedException {

        TwitterClient twitterClient = new TwitterClient();
        Configuration conf = twitterClient.getConfiguration();

//        String[] keywords = {"#championsleague", "#ManchesterUnited"};
        String[] keywords = {"#EU60", "#SurvivorGR"};
        TwitterCrawler crawler = new TwitterCrawler(keywords, conf);
        int total = crawler.stream();
        System.out.println("total " + total);

    }//end main
}//
