package com.app.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;

/**
 * Created by hiba on 10/05/2016.
 */
public class PanneauExtractionCaracteristiquesController {

    @FXML
    private ToggleButton tbLemmatisation;
    @FXML
    private ToggleButton tbLemmatisationPos;
    @FXML
    private ProgressBar pbProgressLemmatisation;
    @FXML
    private ToggleButton tbUnGram;
    @FXML
    private ToggleButton tbBiGram;
    @FXML
    private ToggleButton tbTriGram;
    @FXML
    private ProgressBar pbProgressNgram;
    @FXML
    private Button btnDemarrerExtraction;
    @FXML
    private Button btnVoirExempleResultat;

    @FXML
    private void demarrerExtraction(){
        System.out.println("Demarrer l'extraction..");
    }

    @FXML
    private void voirExempleResultat(){
        System.out.println("Voir un exemple du resultat");
    }
}
