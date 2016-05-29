package com.app.ui.controllers;

import com.app.model.CalculateurStatistiquesApprentissage;
import com.app.model.Tweet;
import com.app.model.database.ConnecteurBaseDeDonnees;
import com.app.model.database.Mot;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

import static com.app.helper.Statics.*;
import static com.app.model.database.ConnecteurBaseDeDonnees.ID_CLASSE_SENTIMENT_POSITIF;

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
        TaskApprentissage taskApprentissage = new TaskApprentissage();
        new Thread( taskApprentissage ).start();
    }

    @FXML
    private void voireExempleResultat(){
        System.out.println("Voir un exemple du resultat");
    }

    private class TaskApprentissage extends Task{

        @Override
        protected Object call() throws Exception {

            Platform.runLater(() -> pbConstructionVocabulaire.setProgress( -1.0f ));
            Platform.runLater(() -> pbCalculProbabilite.setProgress( -1.0f ));

            creerLeVocabulaire();
            calculerLesProbabilite();


            return null;
        }


        private void calculerLesProbabilite() {
            CalculateurStatistiquesApprentissage calculateurStatistiquesApprentissage = new CalculateurStatistiquesApprentissage();

            //calcul priors des classs
            calculateurStatistiquesApprentissage.calculerLesPriors();
            mettreAJourLesPriorsDansBDD( calculateurStatistiquesApprentissage );

            //calcul nombre de mot de chaque classe
            calculateurStatistiquesApprentissage.calculerLesNombreDeMots();
            mettreAJourLesNombresDeMotsDansBDD( calculateurStatistiquesApprentissage );

            //calcul de la taille de vocabulaire
            calculateurStatistiquesApprentissage.calculerLaTailleDeVocabulaire();
            mettreAJourLaTailleDeVocabulaireDeCorpus( calculateurStatistiquesApprentissage );

            //calcul des probabilites de chaque mot
            calculerLesProbabilitesDeChaqueMot( calculateurStatistiquesApprentissage);
        }

        public void calculerLesProbabilitesDeChaqueMot(CalculateurStatistiquesApprentissage calculateurStatistiquesApprentissage) {
            ObservableList<Mot> listDeMotDansLaBDD = CONNECTEUR_BASE_DE_DONNEES.getListeDeMots();
            for (Mot mot : listDeMotDansLaBDD) {

                mot.setProbaPos( calculateurStatistiquesApprentissage.getProbabilitePositiveDuMot( mot ) );
                mot.setProbaNeg( calculateurStatistiquesApprentissage.getProbabiliteNegativeDuMot( mot ) );

                CONNECTEUR_BASE_DE_DONNEES.updateLesProbabilitesDuMot( mot );
            }

        }

        private void mettreAJourLaTailleDeVocabulaireDeCorpus(CalculateurStatistiquesApprentissage calculateurStatistiquesApprentissage) {
            CONNECTEUR_BASE_DE_DONNEES.updateLaTailleDeVocabulaireDeCorpus( calculateurStatistiquesApprentissage );
        }

        private void mettreAJourLesNombresDeMotsDansBDD(CalculateurStatistiquesApprentissage calculateurStatistiquesApprentissage) {
            CONNECTEUR_BASE_DE_DONNEES.updateLesNombresDeMotsDesClasses( calculateurStatistiquesApprentissage );
        }

        private void mettreAJourLesPriorsDansBDD(CalculateurStatistiquesApprentissage calculateurStatistiquesApprentissage) {
            CONNECTEUR_BASE_DE_DONNEES.updateLesPriorsDesClasses( calculateurStatistiquesApprentissage);
        }


        @Override
        protected void succeeded() {
            super.succeeded();
            pbConstructionVocabulaire.setProgress(1);
            pbCalculProbabilite.setProgress(1);

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

    private void creerLeVocabulaire() {

        CONNECTEUR_BASE_DE_DONNEES = new ConnecteurBaseDeDonnees();

        for (int i = 0; i < NOMBRE_DE_TWEETS_SANDER; i++) {                                  //parcourir les tweets
            Tweet tweetCourrant = PIPELINE.getListeDeTweetsApprentissageNettoye().get(i);

            if (tweetCourrant.getSentiment() == -1.0d || tweetCourrant.getSentiment() == 1.0d){   //on prend que les tweets positif et negatif
                //parocurir les gram de chaque tweet (option 1, 2, 3)
                for (String gram: tweetCourrant.getListOfLemmasUnGram() ) {
                    if (existeDansLeVocabulaire(gram)) {
                        //chercher si le gram existe dans la base
                        //Si il existe
                        //incrémenter le nombre d'apparition dans la classe appropriee
                        incrementerLeNombreDApparitionsDansLaClasse(gram, tweetCourrant.getSentiment());


                    } else {
                        //sinon
                        //ajouter le nouveau gram

                        Mot mot = new Mot(-1, 1, gram, 0, 0, 0, 0, N_GRAM_CHOISI);

                        if (getIdClasseSentiment(tweetCourrant.getSentiment()) == ID_CLASSE_SENTIMENT_POSITIF)
                            mot.setNbrApparClassePos(1);
                        else
                            mot.setNbrApparClasseNeg(1);

                        ajouterUnNouveauMot( mot );

                    }
                }
            }

        }

    }

    private void ajouterUnNouveauMot(Mot mot) {
        CONNECTEUR_BASE_DE_DONNEES.ajouterUnMot( mot );
    }

    private void incrementerLeNombreDApparitionsDansLaClasse(String gram, double sentimentTweet) {
        int idClasseSentiment = getIdClasseSentiment( sentimentTweet );
        CONNECTEUR_BASE_DE_DONNEES.updateNombreApparitionGram( gram, idClasseSentiment);
    }

    private int getIdClasseSentiment(double sentimentTweet) {
        if (sentimentTweet == 1.0d)
            return 1;
        else
            return 2;
    }

    private boolean existeDansLeVocabulaire(String gram) {
        return CONNECTEUR_BASE_DE_DONNEES.chercherMot( gram );
    }


}
