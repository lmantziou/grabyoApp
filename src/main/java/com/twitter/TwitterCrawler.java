/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twitter;

import com.twitter.model.TwitterApiResponse;
import java.util.ArrayList;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;

/**
 * Class that provides the main implementation of the twitter crawler. Connects
 * with the REST API and search tweets using specific keywords every couple of
 * seconds.
 *
 * @author lenaki_7
 */
public class TwitterCrawler {

    private String keywords = null;

    private int interval;

    private final TwitterClient twitterClient = new TwitterClient();

    private final Configuration conf = twitterClient.getConfiguration();

    private final Twitter twitter = new TwitterFactory(conf).getInstance();
    //Tweet id that is used to search since the last time the timeline was processed.
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
        long afterInterval = timeNow + (interval * 1000);
        int countTweets = 0;
        //define the tweet id that the application should read relative to the IDs of Tweets it has already processed
        long maxID = 0;
        ArrayList<Status> tweets = new ArrayList<Status>();

        Query query = new Query(keywords);

        //************************ Action *************************
        //max 450 calls in 15 mins -->30/min, 5 calls/10 secs
        while (timeNow < afterInterval) {
            try {
                if (sinceID != 0) {
                    query.setSinceId(sinceID);
                }

                if (maxID != 0) {
                    query.setMaxId(maxID);
                }

                query.setResultType(Query.ResultType.recent);
                query.setCount(100);
                QueryResult result = twitter.search(query);
                tweets.addAll(result.getTweets());

                if (tweets.size() == 100) {
                    countTweets += tweets.size();
                    maxID = tweets.get(tweets.size() - 1).getId() - 1;
                } else {
                    countTweets += tweets.size();
                    break;
                }
                timeNow = System.currentTimeMillis();
            } catch (TwitterException te) {
                System.out.println("Couldn't connect: " + te);
            }
        }

        sinceID = tweets.get(0).getId();
        response.setCount(countTweets);
        response.setSinceID(sinceID);

        return response;
    }//end stream

}//end class
