package com.app.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import static com.app.helper.Statics.CALCULATEUR_DES_STATISTIQUES;

/**
 * Created by Oussama on 31/05/2016.
 */
public class PanneauStatistiquesClassificationController {

    @FXML
    private Text txtTruePositives;
    @FXML
    private Text txtTrueNegatives;
    @FXML
    private Text txtFalsePositives;
    @FXML
    private Text txtFalseNegatives;
    @FXML
    private Text txtNombreDeTweetDeTestPositifs;
    @FXML
    private Text txtNombreDeTweetDeTestNegatifs;
    @FXML
    private Button btnAfficherStat;
    @FXML
    private PieChart pieChart;

    @FXML
    private void afficherStatistiquesClassification(){

        CALCULATEUR_DES_STATISTIQUES.calculerLesStatistiquesdeLaPhaseDeTest();

        txtTruePositives.setText(CALCULATEUR_DES_STATISTIQUES.getTruePositives() + "");
        txtTrueNegatives.setText(CALCULATEUR_DES_STATISTIQUES.getTrueNegatives() + "");
        txtFalsePositives.setText(CALCULATEUR_DES_STATISTIQUES.getFalsePositives() + "");
        txtFalseNegatives.setText(CALCULATEUR_DES_STATISTIQUES.getFalseNegatives() + "");
        txtNombreDeTweetDeTestPositifs.setText( CALCULATEUR_DES_STATISTIQUES.getNombreDeTweetDeTestPositifs() + "");
        txtNombreDeTweetDeTestNegatifs.setText( CALCULATEUR_DES_STATISTIQUES.getNombreDeTweetDeTestNegatifs() + "");

        ObservableList<Data> listeDeData = FXCollections.observableArrayList();
        Data data1 = new Data("Tweets de test positifs", CALCULATEUR_DES_STATISTIQUES.getNombreDeTweetDeTestPositifs() );
        Data data2 = new Data("Tweets de test negatifs", CALCULATEUR_DES_STATISTIQUES.getNombreDeTweetDeTestNegatifs() );
        Data data3 = new Data("Autres sentiments", CALCULATEUR_DES_STATISTIQUES.getNombreDeTweetDeTestAutreSentiment() );

        listeDeData.add(data1);
        listeDeData.add(data2);
        listeDeData.add(data3);

        pieChart.setData(listeDeData);
    }
}
