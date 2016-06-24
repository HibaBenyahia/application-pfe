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
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import static com.app.helper.Statics.*;

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
    private ToggleGroup tgLemmatisationChoix;
    @FXML
    private ToggleGroup tgNgramChoix;

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

            pbProgressLemmatisation.setProgress(0);
            pbProgressNgram.setProgress(0);

            enregistrerLesChoixDeLutilisateur();
            extraireLemmatisation();
            extraireNGram();


            return null;
        }

        private void enregistrerLesChoixDeLutilisateur() {
            //choix de contruction de bag of words
            if (tgLemmatisationChoix.getToggles().get(0).isSelected()){
                LEMMA_CHOISI = LEMMATISATION_SEULE;
            }else {
                LEMMA_CHOISI = LEMMATISATION_AND_POS;
            }

            //choix du Ngram
            if (tgNgramChoix.getToggles().get(0).isSelected()){
                N_GRAM_CHOISI = UN_GRAM;
            }else if (tgNgramChoix.getToggles().get(1).isSelected()){
                N_GRAM_CHOISI = BI_GRAM;
            }else
                N_GRAM_CHOISI = TRI_GRAM;
        }

        private void extraireNGram() {

            DiviseurNGram diviseurNGram = new DiviseurNGram();

            for (int i = 0; i < PIPELINE.getListeDeTweetsApprentissageNettoye().size(); i++) {

                Tweet tweet = PIPELINE.getListeDeTweetsApprentissageNettoye().get(i);

                diviseurNGram.setTweet( tweet );

                tweet.setListOfLemmasBiGram( diviseurNGram.getListOfBigrams(tweet) );
                tweet.setListOfLemmasTriGram( diviseurNGram.getListOfTrigrams(tweet) );

                double progress = (double) i / (double) PIPELINE.getListeDeTweetsApprentissageNettoye().size();
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

                tweet.setListOfPosTags( lemmatiseurStanford.getListeDePosTags() );
                tweet.setListOfLemmasUnGram( lemmatiseurStanford.getListeDeLemmas() );

                double progress = (double) i / (double) PIPELINE.getListeDeTweetsApprentissageNettoye().size();
                Platform.runLater(() -> pbProgressLemmatisation.setProgress(progress));

            }
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
