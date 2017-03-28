/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twitter;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import twitter4j.FilterQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;

/**
 *
 * @author lenaki_7
 */
public class TwitterCrawler {

    private String keywords = null;

    private final BlockingQueue<Status> statuses = new LinkedBlockingQueue<Status>();

    private int interval;

    private final TwitterClient twitterClient = new TwitterClient();

    private final Configuration conf = twitterClient.getConfiguration();

    private final Twitter twitter = new TwitterFactory(conf).getInstance();

    /**
     *
     * @param keywords
     * @param interval
     */
    public TwitterCrawler(String keywords, int interval) {
        //************************ Initialize Variables *************************
        this.keywords = keywords;

        this.interval = interval;

    }

    /**
     *
     * @return @throws TwitterException
     * @throws InterruptedException returns the number of tweets
     */
    public int stream() throws TwitterException, InterruptedException {
        //************************ Variables *************************

         long timeNow = System.currentTimeMillis();
        long afterOneMin = timeNow + interval * 1000;
        
        Query query = new Query(keywords);
        query.setResultType(Query.ResultType.recent);
        query.setCount(100);

        //max 450 calls in 15 mins --> 5 calls/10 secs
        int numberOfTweets = 200;
        long lastID = Long.MAX_VALUE;
        ArrayList<Status> tweets = new ArrayList<Status>();
        while (timeNow < afterOneMin) {
//        while (tweets.size() < numberOfTweets) {
            try {

                //calendar get last minute and put in query.since
                query.setSince("");//our date!!
//                query.setSinceId(lastID);
                QueryResult result = twitter.search(query);
                tweets.addAll(result.getTweets());
                System.out.println("Gathered " + tweets.size() + " tweets" + "\n");
//                for (Status t : tweets) {
//                    System.out.println("text "+t.getText());
//                }

            } catch (TwitterException te) {
                System.out.println("Couldn't connect: " + te);
            }
            query.setMaxId(lastID - 1);
        }
        return numberOfTweets;
    }//end stream

}//end class
