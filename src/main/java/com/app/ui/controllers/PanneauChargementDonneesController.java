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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

import static com.app.helper.NotificationHelper.showNotification;
import static com.app.helper.Statics.*;


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
    private ToggleGroup tgGroupeDeBouttonDeChoix;


    @FXML
    private void chargerLesDonnees(){
        TaskChargeurDeTweet taskChargeurDeTweet = new TaskChargeurDeTweet();
        new Thread( taskChargeurDeTweet ).start();
    }



    private class TaskChargeurDeTweet extends Task{
        private int nbtTweetNeg = 0;

        @Override
        protected Object call() throws Exception {

            enregistrerLeChoixDeDataset();
            chergerLesTweets();


            return null;

        }

        private void chergerLesTweets() {
            //lire fichier CSV en Streaming (3 instructions)
            CsvParserSettings settings = new CsvParserSettings();
            CsvParser csvParser = new CsvParser( settings );

            if (DATASET_CHOISI == SANDERS_DATASET){
                csvParser.beginParsing(FileHelper.getReader(ClassLoader.getSystemClassLoader().getResource("datasets/sanders_learning_dataset.csv").getPath() ) );
                NOMBRE_DE_TWEETS_D_APPRENTISSAGE = NOMBRE_DE_TWEETS_SANDER;
            }
            else{
                csvParser.beginParsing(FileHelper.getReader(ClassLoader.getSystemClassLoader().getResource("datasets/sentiment140_learning_dataset.csv").getPath() ) );
                NOMBRE_DE_TWEETS_D_APPRENTISSAGE = NUMBER_DE_TWEET_DE_SENTIMENT140;
            }


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

                double progress = (double) (numeroTweet) / (double) NOMBRE_DE_TWEETS_D_APPRENTISSAGE;
                Platform.runLater(() -> pbChargement.setProgress( progress ));

            }

        }

        private void enregistrerLeChoixDeDataset() {
            if (tgGroupeDeBouttonDeChoix.getToggles().get(0).isSelected()){
                Statics.DATASET_CHOISI = SANDERS_DATASET;
            }
            else
                Statics.DATASET_CHOISI = SENTIMENT140_DATASET;
        }


        @Override
        protected void succeeded(){
            super.succeeded();
            showNotification("Chargement de tweets a été terminé avec succès !");
        }

        @Override
        protected void failed() {
            super.failed();
            System.out.println("failed");
        }

        @Override
        protected void cancelled() {
            super.cancelled();
            System.out.println("canceled");
        }
    }
}
