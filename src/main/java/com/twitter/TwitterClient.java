/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twitter;

import java.util.ResourceBundle;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * 
 * Class that logins and authenticates using twitter app credentials
 * A configuration builder is constructed to let app make calls in twitter
 * using twitter4j library
 * @author lenaki_7
 */
public class TwitterClient {

    private static final ResourceBundle RESOURCEBUNDLE;

    private static final String OAUTHCONSUMERKEY;
    private static final String OAUTHCONSECRET;
    private static final String OAUTHACCESSTOKEN;
    private static final String OAUTHACCESSTOKENSECRET;

    static {

        RESOURCEBUNDLE = ResourceBundle.getBundle("settings");
        OAUTHCONSUMERKEY = RESOURCEBUNDLE.getString("tw.oAuthConsumerKey");
        OAUTHCONSECRET = RESOURCEBUNDLE.getString("tw.oAuthConsumerSecret");
        OAUTHACCESSTOKEN = RESOURCEBUNDLE.getString("tw.oAuthAccessToken");
        OAUTHACCESSTOKENSECRET = RESOURCEBUNDLE.getString("tw.oAuthAccessTokenSecret");

    }

    public Configuration getConfiguration() {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setJSONStoreEnabled(true)
                .setOAuthConsumerKey(OAUTHCONSUMERKEY)
                .setOAuthConsumerSecret(OAUTHCONSECRET)
                .setOAuthAccessToken(OAUTHACCESSTOKEN)
                .setOAuthAccessTokenSecret(OAUTHACCESSTOKENSECRET);
        Configuration configuration = cb.build();

        return configuration;

    }

}//end class
