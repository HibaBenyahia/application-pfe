package com.app.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

/**
 * Created by Oussama on 10/05/2016.
 */
public class PanneauApprentissageController {
    @FXML
    private ProgressBar pbConstructionVocabulaire;
    @FXML
    private  ProgressBar pbCalculProbabilite;
    @FXML
    private Button btnCommencerApprentissage;
    @FXML
    private Button btnVoirExempleResultat;

    @FXML
    private void commencerApprentissage(){
        System.out.println("Commencer l'apprentissage");
    }

    @FXML
    private void voireExempleResultat(){
        System.out.println("Voir un exemple du resultat");
    }

}
