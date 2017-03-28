/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twitter.model;

import java.io.Serializable;

/**
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
