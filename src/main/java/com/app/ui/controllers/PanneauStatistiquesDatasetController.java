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
 * Created by Oussama on 30/05/2016.
 */
public class PanneauStatistiquesDatasetController {

    @FXML
    private PieChart pieChart;
    @FXML
    private Button btnAfficherStatDataset;
    @FXML
    private Text txtNombreTweetsApprentissage;
    @FXML
    private Text txtNombreTweetsApprentissageApresNettoyage;
    @FXML
    private Text txtNombreDeTweetsPositifs;
    @FXML
    private Text txtNomreDeTweetsNegatif;
    @FXML
    private Text txtNombreDeTweetsDeTypeAutres;

    @FXML
    private void afficherLesStat(){
        ObservableList<Data> listeDeDonneesPourLeChart = FXCollections.observableArrayList();

        CALCULATEUR_DES_STATISTIQUES.calculerLesStatistiquesSurLesDataSetDApprentissage();

        Data data1 = new Data("Tweets Positifs", CALCULATEUR_DES_STATISTIQUES.getNombreDeTweetsDApprentissagePositif());
        Data data2 = new Data("Tweets Negatifs", CALCULATEUR_DES_STATISTIQUES.getNombreDeTweetsDApprentissageNegatif());
        Data data3 = new Data("Autres Tweets", CALCULATEUR_DES_STATISTIQUES.getNombreDeTweetsDApprentissageDAutresSentiments());

        listeDeDonneesPourLeChart.add( data1 );
        listeDeDonneesPourLeChart.add( data2 );
        listeDeDonneesPourLeChart.add( data3 );

        pieChart.setData( listeDeDonneesPourLeChart );

        txtNombreTweetsApprentissage.setText(CALCULATEUR_DES_STATISTIQUES.getNombreDeTweetsDApprentissageAvantNettoyage() + "");
        txtNombreTweetsApprentissageApresNettoyage.setText(CALCULATEUR_DES_STATISTIQUES.getNombreDeTweetsDApprentissageApresNettoyage() +"");
        txtNombreDeTweetsPositifs.setText(CALCULATEUR_DES_STATISTIQUES.getNombreDeTweetsDApprentissagePositif() + "");
        txtNomreDeTweetsNegatif.setText(CALCULATEUR_DES_STATISTIQUES.getNombreDeTweetsDApprentissageNegatif() + "");
        txtNombreDeTweetsDeTypeAutres.setText(CALCULATEUR_DES_STATISTIQUES.getNombreDeTweetsDApprentissageDAutresSentiments() + "");
    }
}
