package com.app.ui.controllers;

import com.app.model.Tweet;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

import static com.app.helper.Statics.NOMBRE_DE_TWEETS_SANDER;
import static com.app.helper.Statics.PIPELINE;

/**
 * Created by hiba on 10/05/2016.
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

    private class TaskApprentissage extends Task{

        @Override
        protected Object call() throws Exception {

            creerLeVocabulaire();
            calculerLesProbabilite();


            return null;
        }

        private void calculerLesProbabilite() {

        }

        private void creerLeVocabulaire() {
            //parcourir les tweets
            for (int i = 0; i < NOMBRE_DE_TWEETS_SANDER; i++) {
                Tweet tweetCourrant = PIPELINE.getListeDeTweetsApprentissageNettoye().get(i);

                //parocurir les gram de chaque tweet (option 1, 2, 3)
                for (String gram: tweetCourrant.getListOfLemmasUnGram() ) {

                    //chercher si le gram existe dans la base


                }


                //Si oui
                //incrémenter le nombre d'apparition dans la classe appropriee
                //sinon
                //ajouter le nouveau gram

            }

        }
    }

}
