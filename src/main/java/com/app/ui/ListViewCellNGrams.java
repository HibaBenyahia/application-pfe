package com.app.ui;

import com.app.helper.ErrorHelper;
import com.app.model.Tweet;
import com.app.ui.controllers.PanneauTweetAvecGramsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;

/**
 * Created by Oussama on 14/05/2016.
 */
public class ListViewCellNGrams extends ListCell<Tweet> {

    private static final String PANNEAU_TWEET_AVEC_GRAMS = "panneau_tweet_avec_grams.fxml";

    @Override
    protected void updateItem(Tweet tweetItem, boolean empty) {
        super.updateItem(tweetItem, empty);

        Parent root = null;

        if (tweetItem != null){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("fxml/"+PANNEAU_TWEET_AVEC_GRAMS));
                root = (Parent) fxmlLoader.load();

                PanneauTweetAvecGramsController panneauTweetAvecGramsController = fxmlLoader.getController();
                panneauTweetAvecGramsController.setTweet( tweetItem );


            } catch (Exception e) {
                ErrorHelper.showErrorDialog( e );
            }
        }

        setGraphic( root );


    }
}
