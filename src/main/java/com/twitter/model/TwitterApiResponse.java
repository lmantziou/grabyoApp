/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twitter.model;

import java.io.Serializable;

/**
 * Class that represents the output of the Twitter mediator service. It's the
 * resource representation of the web service. It returns the number of tweets
 * (count) and the tweet id (sinceID) that last time the twitter API is called.
 *
 * @author EMantziou
 */
public class TwitterApiResponse implements Serializable {

    private int count;

    private long sinceID;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getSinceID() {
        return sinceID;
    }

    public void setSinceID(long sinceID) {
        this.sinceID = sinceID;
    }
}
