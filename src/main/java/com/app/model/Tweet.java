package com.app.model;

import java.util.Date;

/**
 * Created by hiba on 24/04/2016.
 */
public class Tweet {

    private String id;
    private String user;
    private String tweettext;
    private Location location;
    private Date date;
    private int favoris;
    private String langue;
    private String placename;
    private int retweet;
    private double sentiment;

    public Tweet(String id,
                 String user,
                 String tweettext,
                 Location location,
                 Date date, int favoris,
                 String langue,
                 String placename,
                 int retweet,
                 double sentiment) {
        this.id = id;
        this.user = user;
        this.tweettext = tweettext;
        this.location = location;
        this.date = date;
        this.favoris = favoris;
        this.langue = langue;
        this.placename = placename;
        this.retweet = retweet;
        this.sentiment = sentiment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTweettext() {
        return tweettext;
    }

    public void setTweettext(String tweettext) {
        this.tweettext = tweettext;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getFavoris() {
        return favoris;
    }

    public void setFavoris(int favoris) {
        this.favoris = favoris;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public int getRetweet() {
        return retweet;
    }

    public void setRetweet(int retweet) {
        this.retweet = retweet;
    }

    public double getSentiment() {

        return sentiment;
    }

    public void setSentiment(double sentiment) {

        this.sentiment = sentiment;
    }


    @Override
    public String toString() {
        return "Tweet{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", tweettext='" + tweettext + '\'' +
                ", location=" + (location==null?"":location.toString()) +
                ", date=" + date +
                ", favoris=" + favoris +
                ", langue='" + langue + '\'' +
                ", placename='" + placename + '\'' +
                ", retweet=" + retweet +
                ", sentiment=" + sentiment +
                '}';
    }
}
