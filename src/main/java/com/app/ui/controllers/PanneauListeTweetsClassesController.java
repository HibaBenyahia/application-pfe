package com.app.ui.controllers;

import com.app.helper.ErrorHelper;
import com.app.helper.Statics;
import com.app.model.Tweet;
import com.app.ui.ListViewCellTweetClasse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

import static com.app.helper.Statics.PIPELINE;

/**
 * Created by Oussama on 25/05/2016.
 */
public class PanneauListeTweetsClassesController {

    @FXML
    private Button btnAfficherMap;
    @FXML
    private ListView lvListeDesTweetsDeTestClasses;
    private ObservableList<Tweet> listeDeTweetAvecLocation;

    public void afficherLalisteDesTweets(){
        lvListeDesTweetsDeTestClasses.setItems( PIPELINE.getListeDeTweetsDeTestNettoye() );
        lvListeDesTweetsDeTestClasses.setCellFactory((Callback<ListView<Tweet>, ListCell<Tweet>>) listView -> new ListViewCellTweetClasse());

    }

    @FXML
    private void afficherLaMap(){
        listeDeTweetAvecLocation = FXCollections.observableArrayList();
        Statics.PIPELINE.getListeDeTweetsDeTestNettoye().stream().filter(tweet -> tweet.getLocation() != null).forEach(tweet -> listeDeTweetAvecLocation.add(tweet));

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("fxml/panneau_map.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            PanneauMapController panneauMapController = fxmlLoader.getController();
            panneauMapController.setListeDeTweetsAvecGeoLocation( listeDeTweetAvecLocation );

            Scene scene = new Scene(root);
            Stage fenetrecomparaison = new Stage();
            fenetrecomparaison.setScene(scene);
            fenetrecomparaison.setTitle("Affichage des tweets classés sur la carte - PFE HIBA BENYAHIA");
            fenetrecomparaison.show();

        } catch (Exception e) {
            ErrorHelper.showErrorDialog(e);
        }
    }
}
