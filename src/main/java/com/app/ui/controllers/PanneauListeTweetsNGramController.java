package com.app.ui.controllers;

import com.app.model.Tweet;
import com.app.ui.ListViewCellNGrams;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import static com.app.helper.Statics.PIPELINE;

/**
 * Created by Oussama on 25/05/2016.
 */
public class PanneauListeTweetsNGramController {

    @FXML
    private ListView lvListeDesTweetsNGram;

    public void afficherLalisteDesTweets(){
        lvListeDesTweetsNGram.setItems( PIPELINE.getListeDeTweetsApprentissageNettoye() );
        lvListeDesTweetsNGram.setCellFactory((Callback<ListView<Tweet>, ListCell<Tweet>>) listView -> new ListViewCellNGrams());

    }
}
