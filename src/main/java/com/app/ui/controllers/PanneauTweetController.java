package com.app.ui.controllers;

import com.app.model.Tweet;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Created by Oussama on 14/05/2016.
 */
public class PanneauTweetController {

    @FXML
    private Text txtIdTweet;
    @FXML
    private Text txtUser;
    @FXML
    private Text txtTweetText;
    @FXML
    private Text txtLangue;
    @FXML
    private FontAwesomeIconView faivIconCle;
    @FXML
    private Pane pCouleurSentiment;



    private void setCouleurPositive(){
        pCouleurSentiment.setStyle("-fx-background-color: #09a41b;");
        faivIconCle.setStyle("-fx-text-fill: #09a41b;");
        txtIdTweet.setStyle("-fx-text-fill: #09a41b;");
    }

    private void setCouleurNegative(){
        pCouleurSentiment.setStyle("-fx-background-color: #dd0e0e;");
        faivIconCle.setStyle("-fx-text-fill: #dd0e0e;");
        txtIdTweet.setStyle("-fx-text-fill: #dd0e0e;");
    }

    private void setCouleurNeutre(){
        pCouleurSentiment.setStyle("-fx-background-color: #373538;");
        faivIconCle.setStyle("-fx-text-fill: #373538;");
        txtIdTweet.setStyle("-fx-text-fill: #373538;");
    }

    private void setCouleurIrrelevent(){
        pCouleurSentiment.setStyle("-fx-background-color: #dd5a23;");
        faivIconCle.setStyle("-fx-text-fill: #DD5A23;");
        txtIdTweet.setStyle("-fx-text-fill: #DD5A23;");
    }


    public void setTweet(Tweet tweet){
        txtIdTweet.setText( tweet.getId() );
        txtUser.setText( tweet.getUser() );
        txtTweetText.setText( tweet.getTweettext() );
        txtLangue.setText( tweet.getLangue() );
        double sentiment = tweet.getSentiment();

        if (sentiment == -1.0d) {
            setCouleurNegative();
        }else
            if (sentiment == 0.0d){
                setCouleurNeutre();
            }else
                if (sentiment == 1.0d){
                    setCouleurPositive();
                }else
                    if (sentiment == 9.0){
                        setCouleurIrrelevent();
                    }
    }
}
