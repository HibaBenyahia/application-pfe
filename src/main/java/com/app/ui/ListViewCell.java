package com.app.ui;

import com.app.helper.ErrorHelper;
import com.app.model.Tweet;
import com.app.ui.controllers.PanneauTweetController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;

/**
 * Created by Oussama on 14/05/2016.
 */
public class ListViewCell extends ListCell<Tweet> {

    private static final String PANNEAU_TWEET = "panneau_tweet.fxml";

    @Override
    protected void updateItem(Tweet tweetItem, boolean empty) {
        super.updateItem(tweetItem, empty);

        Parent root = null;

        if (tweetItem != null){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("fxml/"+PANNEAU_TWEET));
                root = (Parent) fxmlLoader.load();

                PanneauTweetController panneauTweetController = fxmlLoader.getController();
                panneauTweetController.setTweet( tweetItem );


            } catch (Exception e) {
                ErrorHelper.showErrorDialog( e );
            }
        }

        setGraphic( root );


    }
}
