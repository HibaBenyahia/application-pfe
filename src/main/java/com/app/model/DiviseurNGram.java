package com.app.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Created by Oussama on 25/05/2016.
 */
public class DiviseurNGram {

    public static final int BI_GRAM = 2;
    public static final int TRI_GRAM = 3;

    public static final int LEMMA_SEULE = 0;
    public static final int LEMMA_AND_POS = 1;


    private Tweet tweet;

    public DiviseurNGram(Tweet tweet) {
        this.tweet = tweet;
    }

    public DiviseurNGram() {
    }

    public ObservableList<String> getListOfBigrams(Tweet tweet){
        ObservableList<String> resultat = FXCollections.observableArrayList();

        if (tweet.getListOfLemmasUnGram().size() == 1){
            String biGram = tweet.getListOfLemmasUnGram().get(0);
            resultat.add( biGram );
        }else
            for (int i = 1; i < tweet.getListOfLemmasUnGram().size(); i++) {
            String premierUnGram = tweet.getListOfLemmasUnGram().get( i-1 );
            String deuxiemeUnGram = tweet.getListOfLemmasUnGram().get( i );

            String biGram = premierUnGram+"_"+deuxiemeUnGram;
            resultat.add( biGram );
            }

        return resultat;
    }

    public ObservableList<String> getListOfTrigrams(Tweet tweet){
        ObservableList<String> resultat = FXCollections.observableArrayList();

        if (tweet.getListOfLemmasUnGram().size() <= 2){
            String triGram = "";
            switch ( tweet.getListOfLemmasUnGram().size()){
                case 1 : triGram = tweet.getListOfLemmasUnGram().get(0); break;
                case 2 : triGram = tweet.getListOfLemmasUnGram().get(0) +"_"+ tweet.getListOfLemmasUnGram().get(1); break;
            }
            resultat.add( triGram );
        }else
            for (int i = 2; i < tweet.getListOfLemmasUnGram().size(); i++) {
            String premierUnGram = tweet.getListOfLemmasUnGram().get( i-2 );
            String deuxiemeUnGram = tweet.getListOfLemmasUnGram().get( i-1 );
            String troisiemeUnGram = tweet.getListOfLemmasUnGram().get( i );

            String triGram = premierUnGram+"_"+deuxiemeUnGram+"_"+troisiemeUnGram;
            resultat.add( triGram );
            }

        return resultat;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
}
