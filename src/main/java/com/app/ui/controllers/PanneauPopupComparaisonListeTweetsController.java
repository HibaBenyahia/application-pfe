package com.app.ui.controllers;

import com.app.model.Tweet;
import com.app.ui.ListViewCell;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import static com.app.helper.Statics.PIPELINE;

/**
 * Created by Oussama on 14/05/2016.
 */
public class PanneauPopupComparaisonListeTweetsController {

    @FXML
    private ListView lvTweetsOriginaux;
    @FXML
    private ListView lvTweetsNettoyes;

    public void afficherLesListesDeTweets(){

        configurerLaListesDesTweetsOriginaux();
        configurerLaListesDesTweetsNettoyes();

    }

    private void configurerLaListesDesTweetsOriginaux() {
        lvTweetsOriginaux.setItems( PIPELINE.getListeDeTweetsApprentissage() );
        lvTweetsOriginaux.setCellFactory((Callback<ListView<Tweet>, ListCell<Tweet>>) listView -> new ListViewCell());
    }

    private void configurerLaListesDesTweetsNettoyes() {
        lvTweetsNettoyes.setItems( PIPELINE.getListeDeTweetsApprentissageNettoye() ); //TODO charger les tweets nettoyés
        lvTweetsNettoyes.setCellFactory((Callback<ListView<Tweet>, ListCell<Tweet>>) listView -> new ListViewCell());
    }
}
