package com.app.ui.controllers;

import com.app.model.Tweet;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * Created by hiba on 10/05/2016.
 */
public class PanneauPopupExempleNettoyage {
    @FXML
    private Text txtTweetAvantNettoyage;
    @FXML
    private Text txtTweetApresNettoyage;

    private Tweet tweetAvantNettoyage;
    private Tweet tweetApresNettoyage;


    public void setTweetAvantNettoyage(Tweet tweetAvantNettoyage) {
        this.tweetAvantNettoyage = tweetAvantNettoyage;
        this.txtTweetAvantNettoyage.setText( tweetAvantNettoyage.getTweettext() );
    }

    public void setTweetApresNettoyage(Tweet tweetApresNettoyage) {
        this.tweetApresNettoyage = tweetApresNettoyage;
        this.txtTweetApresNettoyage.setText( tweetApresNettoyage.getTweettext() );
    }
}
