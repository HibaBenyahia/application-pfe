package com.app.ui;

import com.app.helper.ErrorHelper;
import com.app.model.Tweet;
import com.app.ui.controllers.PanneauTweetClasseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;

/**
 * Created by Oussama on 14/05/2016.
 */
public class ListViewCellTweetClasse extends ListCell<Tweet> {

    private static final String PANNEAU_TWEET_CLASSE = "panneau_tweet_classe.fxml";

    @Override
    protected void updateItem(Tweet tweetItem, boolean empty) {
        super.updateItem(tweetItem, empty);

        Parent root = null;

        if (tweetItem != null){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("fxml/"+PANNEAU_TWEET_CLASSE));
                root = (Parent) fxmlLoader.load();

                PanneauTweetClasseController panneauTweetClasseController = fxmlLoader.getController();
                panneauTweetClasseController.setTweet( tweetItem );


            } catch (Exception e) {
                ErrorHelper.showErrorDialog( e );
            }
        }

        setGraphic( root );


    }
}
