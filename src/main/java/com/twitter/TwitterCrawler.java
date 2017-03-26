/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;

/**
 *
 * @author lenaki_7
 */
public class TwitterCrawler {

    private String[] keywords = null;

    private final BlockingQueue<Status> statuses = new LinkedBlockingQueue<Status>();

    private TwitterStream twitterStream;

    /**
     *
     * @param keywords
     * @param conf
     */
    public TwitterCrawler(String[] keywords, Configuration conf) {
        //************************ Initialize Variables *************************
        this.keywords = keywords;

        twitterStream = new TwitterStreamFactory(conf).getInstance();

        StatusListener listener = new StreamStatusListener();
        twitterStream = new TwitterStreamFactory(conf).getInstance();
        twitterStream.addListener(listener);

    }

    /**
     *
     * @return @throws TwitterException
     * @throws InterruptedException returns the number of tweets
     */
    public int stream() throws TwitterException, InterruptedException {
        //************************ Variables *************************
        FilterQuery query = new FilterQuery();

        boolean sample = true;
        int tweetCount = 0;
        Status status;
        //************************ Action *************************

        if (keywords.length > 0) {
            query.track(keywords);
            sample = false;
        }
        if (sample) {
            twitterStream.sample();
        } else {
            twitterStream.filter(query);
        }

        long timeNow = System.currentTimeMillis();
        long afterOneMin = timeNow + 1 * 60 * 1000;
        
        final List<Status> collected = new ArrayList<Status>(20);
        while (timeNow < afterOneMin) {
            status = statuses.take();
            System.out.println("date "+status.getCreatedAt() );
            tweetCount++;
            timeNow = System.currentTimeMillis();
           
        }

        //close streaming
        twitterStream.shutdown();
        twitterStream.clearListeners();
        return tweetCount;

    }//end stream

    private class StreamStatusListener implements StatusListener {

        @Override
        public void onStatus(Status status) {
            System.out.println("status " + status.getText());
            statuses.offer(status); // Add received status to the queue
        }

        @Override
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        }

        @Override
        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        }

        @Override
        public void onException(Exception ex) {
            ex.printStackTrace();
        }

        @Override
        public void onScrubGeo(long userId, long upToStatusId) {
        }

        @Override
        public void onStallWarning(StallWarning warning) {
        }
    };

}//end class
