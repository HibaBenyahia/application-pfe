package com.app.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;

/**
 * Created by Oussama on 29/05/2016.
 */
public class PanneauTestClassification {
    @FXML
    private ProgressIndicator piClassification;
    @FXML
    private Button btnDemarrerClassification;
    @FXML
    private Button btnAfficherResultat;

    @FXML
    private void demarrerClassification(){
        System.out.println("Demarrer Classification");
    }

    @FXML
    private void afficherResultat(){
        System.out.println("Afficher le Resultat");
    }
}
