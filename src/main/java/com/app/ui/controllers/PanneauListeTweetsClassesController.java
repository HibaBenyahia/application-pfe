package com.app.ui.controllers;

import com.app.model.Tweet;
import com.app.ui.ListViewCellTweetClasse;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import static com.app.helper.Statics.PIPELINE;

/**
 * Created by Oussama on 25/05/2016.
 */
public class PanneauListeTweetsClassesController {

    @FXML
    private ListView lvListeDesTweetsDeTestClasses;

    public void afficherLalisteDesTweets(){
        lvListeDesTweetsDeTestClasses.setItems( PIPELINE.getListeDeTweetsDeTestNettoye() );
        lvListeDesTweetsDeTestClasses.setCellFactory((Callback<ListView<Tweet>, ListCell<Tweet>>) listView -> new ListViewCellTweetClasse());

    }
}
