package com.app.ui.controllers;

import com.app.helper.ErrorHelper;
import com.app.helper.NotificationHelper;
import com.app.model.DiviseurNGram;
import com.app.model.LemmatiseurStanford;
import com.app.model.Tweet;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import static com.app.helper.Statics.PIPELINE;

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
        TaskExtracteurDesCaracteristiques taskExtracteurDesCaracteristiques = new TaskExtracteurDesCaracteristiques();
        new Thread( taskExtracteurDesCaracteristiques ).start();
    }

    @FXML
    private void voirExempleResultat(){
        System.out.println("Voir un exemple du resultat");
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("fxml/panneau_liste_tweets_ngrams.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            PanneauListeTweetsNGramController panneauListeTweetsNGramController = fxmlLoader.getController();
            panneauListeTweetsNGramController.afficherLalisteDesTweets();

            Scene scene = new Scene(root);
            Stage fenetrecomparaison = new Stage();
            fenetrecomparaison.setScene(scene);
            fenetrecomparaison.setTitle("Résultat de l'extraction des sacs mots des tweets");
            fenetrecomparaison.show();

        } catch (Exception e) {
            ErrorHelper.showErrorDialog(e);
        }
    }

    private class TaskExtracteurDesCaracteristiques extends Task{

        @Override
        protected Object call() throws Exception {

            extraireLemmatisation();
            extraireNGram();


            return null;
        }

        private void extraireNGram() {

            DiviseurNGram diviseurNGram = new DiviseurNGram();

            for (int i = 0; i < PIPELINE.getListeDeTweetsApprentissageNettoye().size(); i++) {

                Tweet tweet = PIPELINE.getListeDeTweetsApprentissageNettoye().get(i);

                diviseurNGram.setTweet( tweet );

                tweet.setListOfLemmasBiGram( diviseurNGram.getListOfBigrams(tweet) );
                tweet.setListOfLemmasTriGram( diviseurNGram.getListOfTrigrams(tweet) );

                double progress = i * 100 / PIPELINE.getListeDeTweetsApprentissageNettoye().size();
                Platform.runLater(() -> pbProgressNgram.setProgress(progress));
            }
        }

        @Override
        protected void succeeded() {
            super.succeeded();
            NotificationHelper.showNotification( "Extraction des caractéristiques a été terminé avec succès !");
            System.out.println( PIPELINE.getListeDeTweetsApprentissageNettoye().toString() );
        }

        private void extraireLemmatisation() {

            LemmatiseurStanford lemmatiseurStanford;

            for (int i = 0; i < PIPELINE.getListeDeTweetsApprentissageNettoye().size(); i++) {

                Tweet tweet = PIPELINE.getListeDeTweetsApprentissageNettoye().get(i);

                lemmatiseurStanford = new LemmatiseurStanford( tweet.getTweettext() );
                lemmatiseurStanford.appliquerLaLemmatisationEtPosTag();

                tweet.setListOfLemmasUnGram( lemmatiseurStanford.getListeDeLemmas() );
                tweet.setListOfPosTags( lemmatiseurStanford.getListeDePosTags() );

                double progress = i * 100 / PIPELINE.getListeDeTweetsApprentissageNettoye().size();
                Platform.runLater(() -> pbProgressLemmatisation.setProgress(progress));

            }
        }
    }
}
