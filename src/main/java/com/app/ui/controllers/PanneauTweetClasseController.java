package com.app.ui.controllers;

import com.app.model.Tweet;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Iterator;

import static com.app.helper.Statics.PIPELINE;

/**
 * Created by Oussama on 14/05/2016.
 */
public class PanneauTweetClasseController {

    @FXML
    private Text txtIdTweet;
    @FXML
    private Text txtUser;
    @FXML
    private Text txtTweetText;
    @FXML
    private Text txtTweetTextNettoye;
    @FXML
    private Text txtLangue;
    @FXML
    private Text txtTweetUnGram;
    @FXML
    private Text txtTweetBiGram;
    @FXML
    private Text txtTweetTriGram;
    @FXML
    private FontAwesomeIconView faivIconCle;
    @FXML
    private Pane pCouleurSentiment;
    @FXML
    private Pane pCouleurSentimentClassification;
    @FXML
    private Text txtTweetPosTags;



    private void setCouleurPositive(){
        pCouleurSentiment.setStyle("-fx-background-color: #09a41b;");
    }

    private void setCouleurNegative(){
        pCouleurSentiment.setStyle("-fx-background-color: #dd0e0e;");
    }

    private void setCouleurNeutre(){
        pCouleurSentiment.setStyle("-fx-background-color: #373538;");
    }

    private void setCouleurIrrelevent(){
        pCouleurSentiment.setStyle("-fx-background-color: #dd5a23;");
    }

    private void setCouleurClassificationPositive(){
        pCouleurSentimentClassification.setStyle("-fx-background-color: #09a41b;");
    }

    private void setCouleurClassificationNegative(){
        pCouleurSentimentClassification.setStyle("-fx-background-color: #dd0e0e;");
    }

    private void setCouleurClassificationNeutre(){
        pCouleurSentimentClassification.setStyle("-fx-background-color: #373538;");
    }

    private void setCouleurClassificationIrrelevent(){
        pCouleurSentimentClassification.setStyle("-fx-background-color: #dd5a23;");
    }


    public void setTweet(Tweet tweet){
        txtIdTweet.setText( tweet.getId() );
        txtUser.setText( tweet.getUser() );
        String textTweetOriginal = "";
        for (Iterator<Tweet> iterator = PIPELINE.getListeDeTweetsDeTest().iterator(); iterator.hasNext(); ) {
            Tweet tweetTestOriginal = iterator.next();
            if (tweetTestOriginal.getId().equals(tweet.getId())) {
                textTweetOriginal = tweetTestOriginal.getTweettext();
                break;
            }
        }
        txtTweetText.setText( textTweetOriginal );
        txtTweetTextNettoye.setText( tweet.getTweettext() );
        txtLangue.setText( tweet.getLangue() );
        txtTweetUnGram.setText( tweet.getListOfLemmasUnGram().toString() );
        txtTweetPosTags.setText( tweet.getListOfPosTags().toString() );
        txtTweetBiGram.setText( tweet.getListOfLemmasBiGram().toString() );
        txtTweetTriGram.setText( tweet.getListOfLemmasTriGram().toString() );
        double sentiment = tweet.getSentiment();
        double sentimentClassification = tweet.getSentimentApresClassification();

        if (sentiment == -1.0d) {
            setCouleurNegative();
        }else if (sentiment == 0.0d){
                setCouleurNeutre();
            }else if (sentiment == 1.0d){
                    setCouleurPositive();
                }else if (sentiment == 9.0)
            setCouleurIrrelevent();

        if (sentimentClassification == -1.0d) {
            setCouleurClassificationNegative();
        }else if (sentimentClassification == 0.0d){
            setCouleurClassificationNeutre();
        }else if (sentimentClassification == 1.0d){
            setCouleurClassificationPositive();
        }else if (sentimentClassification == 9.0)
            setCouleurClassificationIrrelevent();
    }
}
