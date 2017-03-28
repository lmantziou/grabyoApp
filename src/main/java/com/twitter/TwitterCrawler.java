/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twitter;

import com.twitter.model.TwitterApiResponse;
import java.util.ArrayList;
import java.util.Date;
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

    private long sinceID;

    /**
     *
     * @param keywords
     * @param interval
     */
    public TwitterCrawler(String keywords, int interval, long sinceID) {
        //************************ Initialize Variables *************************
        this.keywords = keywords;

        this.interval = interval;
        this.sinceID = sinceID;
    }

    /**
     *
     * @return @throws TwitterException
     * @throws InterruptedException returns the number of tweets
     */
    public TwitterApiResponse stream() throws TwitterException, InterruptedException {
        //************************ Variables *************************
        TwitterApiResponse response = new TwitterApiResponse();
        long timeNow = System.currentTimeMillis();
        long afterOneMin = timeNow + (interval * 1000);

        Query query = new Query(keywords);

//        long sinceID = 0;
        //max 450 calls in 15 mins -->30/min, 5 calls/10 secs
        int numberOfTweets = 100;
        ArrayList<Status> tweets = new ArrayList<Status>();
        int count = 0;
        long maxID = 0;
        while (timeNow < afterOneMin) {
//        while (tweets.size() < numberOfTweets) {
            try {

                //************************
                // we get in request the sinceID 
                if (sinceID != 0) {
                    query.setSinceId(sinceID);
                }

                if (maxID != 0) {
                    query.setMaxId(maxID);
                }

                //*************************
                query.setResultType(Query.ResultType.recent);
                query.setCount(100);
                QueryResult result = twitter.search(query);
                tweets.addAll(result.getTweets());

                if (tweets.size() == 100) {

                    System.out.println("==100");
                    System.out.println("sinceID " + sinceID);
                    count += tweets.size();
                    maxID = tweets.get(tweets.size() - 1).getId() - 1;
                } else {
                    System.out.println("not ==100");
                    count += tweets.size();
                    break;
                }

                System.out.println("Gathered " + tweets.size() + " tweets" + "\n");
                timeNow = System.currentTimeMillis();
            } catch (TwitterException te) {
                System.out.println("Couldn't connect: " + te);
            }

            System.out.println("size " + tweets.size());
        }

        sinceID = tweets.get(0).getId();
        System.out.println("sinceID out " + sinceID);

//        for (int i = 0; i < tweets.size(); i++) {
//            Status t = (Status) tweets.get(i);
//
//            System.out.println("created " + t.getCreatedAt() + " id " + t.getText());
//        }
        response.setCount(count);
        response.setSinceID(sinceID);
        return response;
    }//end stream

}//end class
