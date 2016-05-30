package com.app.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    private double probabiliteDeLaClassePositive;
    private double probabiliteDeLaClasseNegative;
    private double sentimentApresClassification;


    private ObservableList<String> listOfLemmasUnGram;
    private ObservableList<String> listOfPosTags;
    private ObservableList<String> listOfLemmasBiGram;
    private ObservableList<String> listOfLemmasTriGram;

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

        inistialiserLesListeDeNGram();
    }

    public double getProbabiliteDeLaClassePositive() {
        return probabiliteDeLaClassePositive;
    }

    public void setProbabiliteDeLaClassePositive(double probabiliteDeLaClassePositive) {
        this.probabiliteDeLaClassePositive = probabiliteDeLaClassePositive;
    }

    public double getProbabiliteDeLaClasseNegative() {
        return probabiliteDeLaClasseNegative;
    }

    public void setProbabiliteDeLaClasseNegative(double probabiliteDeLaClasseNegative) {
        this.probabiliteDeLaClasseNegative = probabiliteDeLaClasseNegative;
    }

    public double getSentimentApresClassification() {
        return sentimentApresClassification;
    }

    public void setSentimentApresClassification(double sentimentApresClassification) {
        this.sentimentApresClassification = sentimentApresClassification;
    }

    private void inistialiserLesListeDeNGram() {
        this.listOfLemmasUnGram = FXCollections.observableArrayList();
        this.listOfLemmasBiGram = FXCollections.observableArrayList();
        this.listOfLemmasTriGram = FXCollections.observableArrayList();
        this.listOfPosTags = FXCollections.observableArrayList();
    }

    public Tweet() {

    }

    public Tweet(String id, String user, String tweettext, String langue, double sentiment) {
        this.id = id;
        this.user = user;
        this.tweettext = tweettext;
        this.langue = langue;
        this.sentiment = sentiment;

        inistialiserLesListeDeNGram();
    }

    public Tweet(Tweet other) {
        this.id = other.id;
        this.user = other.user;
        this.tweettext = other.tweettext;
        this.location = other.location;
        this.date = other.date;
        this.favoris = other.favoris;
        this.langue = other.langue;
        this.placename = other.placename;
        this.retweet = other.retweet;
        this.sentiment = other.sentiment;

        this.listOfLemmasUnGram = other.listOfLemmasUnGram;
        this.listOfPosTags = other.listOfPosTags;
        this.listOfLemmasBiGram = other.listOfLemmasBiGram;
        this.listOfLemmasTriGram = other.listOfLemmasTriGram;
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

    public ObservableList<String> getListOfLemmasUnGram() {
        return listOfLemmasUnGram;
    }

    public void setListOfLemmasUnGram(ObservableList<String> listOfLemmasUnGram) {
        this.listOfLemmasUnGram = listOfLemmasUnGram;
    }

    public ObservableList<String> getListOfPosTags() {
        return listOfPosTags;
    }

    public void setListOfPosTags(ObservableList<String> listOfPosTags) {
        this.listOfPosTags = listOfPosTags;
    }

    public ObservableList<String> getListOfLemmasBiGram() {
        return listOfLemmasBiGram;
    }

    public void setListOfLemmasBiGram(ObservableList<String> listOfLemmasBiGram) {
        this.listOfLemmasBiGram = listOfLemmasBiGram;
    }

    public ObservableList<String> getListOfLemmasTriGram() {
        return listOfLemmasTriGram;
    }

    public void setListOfLemmasTriGram(ObservableList<String> listOfLemmasTriGram) {
        this.listOfLemmasTriGram = listOfLemmasTriGram;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", tweettext='" + tweettext + '\'' +
                ", location=" + location +
                ", date=" + date +
                ", favoris=" + favoris +
                ", langue='" + langue + '\'' +
                ", placename='" + placename + '\'' +
                ", retweet=" + retweet +
                ", sentiment=" + sentiment +
                ", probabiliteDeLaClassePositive=" + probabiliteDeLaClassePositive +
                ", probabiliteDeLaClasseNegative=" + probabiliteDeLaClasseNegative +
                ", sentimentApresClassification=" + sentimentApresClassification +
                ", listOfLemmasUnGram=" + listOfLemmasUnGram +
                ", listOfPosTags=" + listOfPosTags +
                ", listOfLemmasBiGram=" + listOfLemmasBiGram +
                ", listOfLemmasTriGram=" + listOfLemmasTriGram +
                '}';
    }

    public Object[] toTableOfObjects(){
        Object [] result = new Object[11];
        result[0] = this.getId();
        result[1] = this.getUser();
        result[2] = this.getTweettext();
        if (this.location == null){
            result[3] = null;   //lat
            result[4] = null;   //long
        }else {
            result[3] = location.getLatitude();    //lat
            result[4] = location.getLongitude();   //long
        }
        result[5] = this.getDate().toString();
        result[6] = this.getFavoris();
        result[7] = this.getLangue();
        if (this.placename == null){
            result[8] = null;
        }else{
            result[8] = this.getPlacename();
        }
        result[9] = this.getRetweet();
        result[10] = this.getSentiment();

        return result;
    }
}
