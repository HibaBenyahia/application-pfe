package com.app.ui.controllers;

import com.app.helper.FileHelper;
import com.app.helper.Statics;
import com.app.model.Tweet;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import static com.app.helper.NotificationHelper.showNotification;
import static com.app.helper.Statics.NOMBRE_DE_TWEETS_SANDER;


/**
 * Created by hiba on 10/05/2016.
 */
public class PanneauChargementDonneesController {

    @FXML
    private GridPane panneauChargementDonnees;
    @FXML
    private Button btnChargerLesDonnees;
    @FXML
    private ToggleButton btnSanders;
    @FXML
    private ToggleButton btnSentiment140;
    @FXML
    private ProgressBar pbChargement;
    @FXML

    private void chargerLesDonnees(){
        TaskChargeurDeTweet taskChargeurDeTweet = new TaskChargeurDeTweet();
        new Thread( taskChargeurDeTweet ).start();
    }



    private class TaskChargeurDeTweet extends Task{

        @Override
        protected Object call() throws Exception {

            //lire fichier CSV en Streaming (3 instructions)
            CsvParserSettings settings = new CsvParserSettings();
            CsvParser csvParser = new CsvParser( settings );
            csvParser.beginParsing(FileHelper.getReader(ClassLoader.getSystemClassLoader().getResource("datasets/sanders_learning_dataset.csv").getPath() ) );

            //construire la liste des tweets
            int numeroTweet = 0;
            String [] ligneCsv;
            while ( (ligneCsv = csvParser.parseNext()) != null){ //lakan mazalet une autre ligne

                String idTweet = ligneCsv[0];
                String user = ligneCsv[1];
                String tweettext = ligneCsv[2];
                String langue = ligneCsv[7];
                double sentiment = Double.parseDouble(ligneCsv[10]);

                Tweet tweet = new Tweet(idTweet, user, tweettext, langue, sentiment);

                Statics.PIPELINE.ajouterUnTweetdApprentissage( tweet );

                numeroTweet++;
                double progress = numeroTweet * 100 / NOMBRE_DE_TWEETS_SANDER;
                Platform.runLater(() -> pbChargement.setProgress(progress));
            }

            return null;

        }


        @Override
        protected void succeeded(){
            super.succeeded();
            showNotification("Chargement de tweets a été terminé avec succès !");
        }
    }
}
