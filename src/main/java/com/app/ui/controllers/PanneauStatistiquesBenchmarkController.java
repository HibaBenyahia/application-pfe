package com.app.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import static com.app.helper.Statics.CALCULATEUR_DES_STATISTIQUES;

/**
 * Created by Oussama on 31/05/2016.
 */
public class PanneauStatistiquesBenchmarkController {

    @FXML
    private Button btnAfficherStat;
    @FXML
    private Text txtAccuracy;
    @FXML
    private Text txtPrecision;
    @FXML
    private Text txtRecall;
    @FXML
    private Text txtFMesure;

    @FXML
    private void afficherLesStatBenchmark(){
        System.out.println( "afficher benchmark");

        CALCULATEUR_DES_STATISTIQUES.calculerBenchmark();

        txtAccuracy.setText(CALCULATEUR_DES_STATISTIQUES.getAccuracy() +"");
        txtPrecision.setText(CALCULATEUR_DES_STATISTIQUES.getPrecision() + "");
        txtRecall.setText( CALCULATEUR_DES_STATISTIQUES.getRecall() + "");
        txtFMesure.setText(CALCULATEUR_DES_STATISTIQUES.getfMesure() +"");

    }
}
