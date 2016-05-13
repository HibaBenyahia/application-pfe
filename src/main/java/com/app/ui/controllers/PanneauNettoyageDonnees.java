package com.app.ui.controllers;

import com.app.helper.ErrorHelper;
import com.app.model.Tweet;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.ToggleSwitch;

import static com.app.helper.NotificationHelper.showNotification;
import static com.app.helper.Statics.NOMBRE_DE_TWEETS_SANDER;
import static com.app.helper.Statics.PIPELINE;

/**
 * Created by hiba on 10/05/2016.
 */
public class PanneauNettoyageDonnees {

    private final String PANNEAU_POPUP_EXEMPLE_NETTOYAGE = "panneau_popup_exemple_nettoyage.fxml";

    /*
    *  Les 8 Toggle Switch
    */
    @FXML
    private ToggleSwitch tsSupprimerTweetsSupperflus;
    @FXML
    private ToggleSwitch tsSupprimerTweetsDupliques;
    @FXML
    private ToggleSwitch tsSupprimerEmoticones;
    @FXML
    private ToggleSwitch tsSupprimerPonctuation;
    @FXML
    private ToggleSwitch tsSupprimerLiens;
    @FXML
    private ToggleSwitch tsSupprimerArobas;
    @FXML
    private ToggleSwitch tsNormaliserText;
    @FXML
    private ToggleSwitch tsEliminerMotVide;

    /*
    * Les 8 Progress Bars
    */
    @FXML
    private ProgressBar pbSupprimerTweetsSupperflus;
    @FXML
    private ProgressBar pbSupprimerTweetsDupliques;
    @FXML
    private ProgressBar pbSupprimerEmoticones;
    @FXML
    private ProgressBar pbSupprimerPonctuation;
    @FXML
    private ProgressBar pbSupprimerLiens;
    @FXML
    private ProgressBar pbSupprimerArobas;
    @FXML
    private ProgressBar pbNormaliserText;
    @FXML
    private ProgressBar pbEliminerMotVide;

    @FXML
    private Button btnDemarrerNettoyage;
    @FXML
    private Button btnVoirExempleResultat;

    @FXML
    private void demarrerNettoyage(){
        TaskNettoyeurDeTweet taskNettoyeurDeTweet = new TaskNettoyeurDeTweet();
        new Thread(taskNettoyeurDeTweet).start();
    }

    @FXML
    private void voirExempleResultat(){
        System.out.println("Voir un exemple du résultat");
        showPopupExempleNettoyage();
    }

    private void showPopupExempleNettoyage() {
        try {
            Tweet tweetAvantNettoyage = new Tweet(); //TODO avoir un vrai tweet avant nettoyage depuis PAIPELINE
            Tweet tweetApresNettoyage = new Tweet(); //TODO avoir un vrai tweet après nettoyage depuis PAIPELINE
            tweetAvantNettoyage.setTweettext("Holo holo holololo popopo ppp opopopiiirgge  efgerg zfzg ");
            tweetApresNettoyage.setTweettext("Holo holo ppp popiiirgge  efgerg zfzg ");

            PopOver popOver = new PopOver();
            popOver.setTitle("Exemple d'un tweet nettoyé");
            popOver.setHeaderAlwaysVisible(true);
            popOver.show( btnVoirExempleResultat );

            FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("fxml/"+PANNEAU_POPUP_EXEMPLE_NETTOYAGE));
            Parent root = (Parent) fxmlLoader.load();


            PanneauPopupExempleNettoyage panneauPopupExempleNettoyage = fxmlLoader.getController();
            panneauPopupExempleNettoyage.setTweetAvantNettoyage( tweetAvantNettoyage );
            panneauPopupExempleNettoyage.setTweetApresNettoyage( tweetApresNettoyage );

            popOver.setContentNode(root);

        } catch (Exception e) {
            ErrorHelper.showErrorDialog( e );
        }
    }

    //une classe qui comporte 8 methodes
    private class TaskNettoyeurDeTweet extends Task{

        @Override
        protected Object call() throws Exception {

            eleminerLesTweetsSupperflus();


            return null;
        }

        private void eleminerLesTweetsSupperflus() {

            for (int i = 0; i < PIPELINE.getListeDeTweetsApprentissage().size() ; i++) {
                Tweet tweetdApprentissage = PIPELINE.getListeDeTweetsApprentissage().get(i);
                if( ! tweetdApprentissage.getLangue().equals("en")){
                    PIPELINE.supprimerLeTweetDapprentissage( tweetdApprentissage );
                }
                double progress = i * 100 / NOMBRE_DE_TWEETS_SANDER;
                Platform.runLater(() -> pbSupprimerTweetsSupperflus.setProgress(progress));
            }
        }

        @Override
        protected void succeeded(){
            super.succeeded();
            showNotification("Suppression des tweets supperflus a été terminé avec succès !");
        }
    }
}
