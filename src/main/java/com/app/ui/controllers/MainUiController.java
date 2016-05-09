package com.app.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by hiba on 09/05/2016.
 */
public class MainUiController {
    @FXML
    private Button btnChargementDeDonnees;

    @FXML
    private Button btnNettoyageDeTweets;

    @FXML
    private BorderPane panneauPrincipale;

    @FXML
    private void afficherPaneauChargementTweets(){

        try {
        System.out.println("afficher panneau tweets");
        FXMLLoader fxmlLoader = new FXMLLoader( ClassLoader.getSystemClassLoader().getResource("fxml/panneau_chargement_donnees.fxml"));
            Parent panneauProchain = fxmlLoader.load();

            panneauPrincipale.setCenter( null );
            panneauPrincipale.setCenter( panneauProchain );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
