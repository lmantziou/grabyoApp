//package com.twitter;
// 
// import java.util.ArrayList;
// import twitter4j.Query;
// import twitter4j.QueryResult;
// import twitter4j.Status;
// import twitter4j.Twitter;
// import twitter4j.TwitterException;
// import twitter4j.TwitterFactory;
// import twitter4j.conf.ConfigurationBuilder;
// 
// /**
//  *
//  * @author lenaki_7
//  */
// public class tweets2 {
//  
//     public static void main(String[] args) throws Exception 
//   {
// 
//       String oAuthConsumerKey = "gO3oK49CFpWrRJkLfTgA";
// 		String oAuthConsumerSecret = "5hrr0hJ6yZbtrsfPRQMJqlkzEU2R3euaHRorL7CavlY";
// 		String oAuthAccessToken = "263582396-dFYVtmqKW7UoKXEww4rMTH5HJNAFP6FszMGjxcKc";
// 		String oAuthAccessTokenSecret = "DerQ1RvVgNKAK8sHijj2hB2OA6z2J483lg0GnP6jCas";
//       
//     ConfigurationBuilder cb = new ConfigurationBuilder();
//     cb.setDebugEnabled(true)
//       .setOAuthConsumerKey(oAuthConsumerKey)
//       .setOAuthConsumerSecret(oAuthConsumerSecret)
//       .setOAuthAccessToken(oAuthAccessToken)
//       .setOAuthAccessTokenSecret(oAuthAccessTokenSecret);
//     Twitter twitter = new TwitterFactory(cb.build()).getInstance();
//     Query query = new Query("#survivorGR");
//     int numberOfTweets = 500;
//     long lastID = Long.MAX_VALUE;
//     ArrayList<Status> tweets = new ArrayList<Status>();
//     while (tweets.size () < numberOfTweets) {
//       if (numberOfTweets - tweets.size() > 100)
//         query.setCount(100);
//       else 
//         query.setCount(numberOfTweets - tweets.size());
//       try {
//           
//           //calendar get last minute and put in query.since
//           query.setSince("");//our date!!
//         QueryResult result = twitter.search(query);
//         tweets.addAll(result.getTweets());
//         System.out.println("Gathered "  tweets.size()  " tweets""\n");
//         for (Status t: tweets) 
//           if(t.getId() < lastID) 
//               lastID = t.getId();
// 
//       }
// 
//       catch (TwitterException te) {
//         System.out.println("Couldn't connect: "  te);
//       }
//       query.setMaxId(lastID-1);
//     }
// 
//     for (int i = 0; i < tweets.size(); i) {
//       Status t = (Status) tweets.get(i);
// 
//      // GeoLocation loc = t.getGeoLocation();
// 
//       String user = t.getUser().getScreenName();
//       String msg = t.getText();
//       //String time = "";
//       //if (loc!=null) {
//         //Double lat = t.getGeoLocation().getLatitude();
//         //Double lon = t.getGeoLocation().getLongitude();*/
////        System.out. println(i  " USER: "  user  " wrote: "  msg  "\n");
//       } 
//       //else 
//         //System.out.println(i  " USER: "  user  " wrote: "  msg"\n");
//     }
// 
// }