/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twitter.rest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import twitter4j.FilterQuery;
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
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

/**
 *
 * @author lenaki_7
 */
public class TwitterCrawler {

    private static final ResourceBundle RESOURCEBUNDLE;

    private static final String OAUTHCONSUMERKEY = "gO3oK49CFpWrRJkLfTgA";
    private static final String OAUTHCONSECRET = "5hrr0hJ6yZbtrsfPRQMJqlkzEU2R3euaHRorL7CavlY";
    private static final String OAUTHACCESSTOKEN = "263582396-dFYVtmqKW7UoKXEww4rMTH5HJNAFP6FszMGjxcKc";
    private static final String OAUTHACCESSTOKENSECRET = "DerQ1RvVgNKAK8sHijj2hB2OA6z2J483lg0GnP6jCas";

    static {

        RESOURCEBUNDLE = ResourceBundle.getBundle("settings");
//          OAUTHCONSUMERKEY = RESOURCEBUNDLE.getString("tw.oAuthConsumerKey");
//          OAUTHCONSECRET = RESOURCEBUNDLE.getString("tw.oAuthConsumerSecret");
//          OAUTHACCESSTOKEN = RESOURCEBUNDLE.getString("tw.oAuthAccessToken");
//        OAUTHACCESSTOKENSECRET = RESOURCEBUNDLE.getString("tw.oAuthAccessTokenSecret");

    }

    public static void main(String args[]) {
        System.out.println("***************");
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setJSONStoreEnabled(true)
                .setOAuthConsumerKey(OAUTHCONSUMERKEY)
                .setOAuthConsumerSecret(OAUTHCONSECRET)
                .setOAuthAccessToken(OAUTHACCESSTOKEN)
                .setOAuthAccessTokenSecret(OAUTHACCESSTOKENSECRET);
//        Configuration conf = cb.build();

//        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        
        StatusListener listener = new StatusListener() {
            int tweetCount = 0;

            @Override
            public void onStatus(Status status) {
                storeInFile(status);
               

            }
            
            private void storeInFile(Status status) {
                System.out.println("we are in file ");
                 System.out.println("status "+status.getCreatedAt());
				
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

        FilterQuery query = new FilterQuery("#survivorGR");

        //set the configuration
        TwitterStream twitterStream = new TwitterStreamFactory(
                cb.build()).getInstance();
        twitterStream.addListener(listener);

        twitterStream.sample();
        
        twitterStream.filter(query);

    }//end main

}//end clas
