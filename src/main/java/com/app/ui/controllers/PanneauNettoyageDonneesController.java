package com.app.ui.controllers;

import com.app.helper.ErrorHelper;
import com.app.model.Emoticone;
import com.app.model.Tweet;
import com.app.temp.LecteurFichierEmoticones;
import com.app.temp.LecteurFichierMotsVides;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;

import static com.app.helper.NotificationHelper.showNotification;
import static com.app.helper.Statics.*;

/**
 * Created by hiba on 10/05/2016.
 */
public class PanneauNettoyageDonneesController {

    private final String PANNEAU_POPUP_EXEMPLE_NETTOYAGE = "panneau_popup_exemple_nettoyage.fxml";
    private final String PANNEAU_POPUP_COMPARAISON_TWEETS = "panneau_popup_comparaison_liste_tweets.fxml";

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
    private Text txtTest;

    @FXML
    private void demarrerNettoyage() {
        TaskNettoyeurDeTweet taskNettoyeurDeTweet = new TaskNettoyeurDeTweet();
        new Thread(taskNettoyeurDeTweet).start();

    }


    @FXML
    private void showFenetreComparaisonTweets() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("fxml/" + PANNEAU_POPUP_COMPARAISON_TWEETS));
            Parent root = (Parent) fxmlLoader.load();

            PanneauPopupComparaisonListeTweetsController panneauPopupComparaisonListeTweetsController = fxmlLoader.getController();
            panneauPopupComparaisonListeTweetsController.afficherLesListesDeTweets();

            Scene scene = new Scene(root);
            Stage fenetrecomparaison = new Stage();
            fenetrecomparaison.setScene(scene);
            fenetrecomparaison.setTitle("Compaéraison entre les tweets originaux et les tweets néttoyés");
            fenetrecomparaison.show();

        } catch (Exception e) {
            ErrorHelper.showErrorDialog(e);
        }
    }




    //une classe qui comporte 8 methodes
    private class TaskNettoyeurDeTweet extends Task {

        @Override
        protected Object call() throws Exception {

            eleminerLesTweetsSupperflus();
            //eliminerLesTweetsRepetes();
            eliminerURL();
            eliminerNomUtilisateur();
            eliminerEmoticones();
            normaliser();
            eliminerMotsVide();
            eliminerPonctuation();

            return null;
        }

        private void eleminerLesTweetsSupperflus() {

            for (int i = 0; i < NOMBRE_DE_TWEETS_D_APPRENTISSAGE; i++) {

                Tweet tweetdApprentissage = new Tweet(PIPELINE.getListeDeTweetsApprentissage().get(i));
                if (tweetdApprentissage.getLangue().equals("en")) { //si le tweet anglais
                    PIPELINE.ajouterUnTweetdApprentissageAlaListeNottoyee(tweetdApprentissage); //on l'ajout a la liste des tweets nettoyés
                }

                double progress = (double) i / (double) NOMBRE_DE_TWEETS_D_APPRENTISSAGE;
                Platform.runLater(() -> pbSupprimerTweetsSupperflus.setProgress(progress));
            }

            //mettre a jour le nombre de tweets d'apprentissage nettoye
            NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES = PIPELINE.getListeDeTweetsApprentissageNettoye().size();
        }

        private void eliminerLesTweetsRepetes() {


            for (int i = 0; i < NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES; i++) {
                final int inti = i;
                Platform.runLater(() -> txtTest.setText(inti+""));

                int nbrRepetitionsDuTweeti = 0; //un conteur de répétition  de chaque tweet donc sa place ici..
                Tweet tweetApprentissage = PIPELINE.getListeDeTweetsApprentissageNettoye().get(i); //j'ai récupéré le tweet nume i

                //si il n'existe pas dans la liste nettoyer alors ajouté sinon supprimé
                for (int j = 0; j < NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES; j++) {
                    Tweet tweetAtester = PIPELINE.getListeDeTweetsApprentissageNettoye().get(j);

                    if (tweetApprentissage.getTweettext().equals(tweetAtester.getTweettext())) {
                        nbrRepetitionsDuTweeti++;
                        if (nbrRepetitionsDuTweeti > 1) {
                            PIPELINE.getListeDeTweetsApprentissageNettoye().remove(i);
                            break;
                        }
                    }

                }
                double progress = (double) (i) / (double) NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES;
                Platform.runLater(() -> pbSupprimerTweetsDupliques.setProgress(progress));
            }

            //mettre a jour le nombre de tweets de sanders
            NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES = PIPELINE.getListeDeTweetsApprentissageNettoye().size();


        }

        private void eliminerEmoticones() {
            // lire le fichier emoticones
            LecteurFichierEmoticones lecteurFichierEmoticones = new LecteurFichierEmoticones();
            lecteurFichierEmoticones.recupererLesEmoticones();

            //parcours de la liste nettoye
            //remplacer les emoticones dans chaque tweet nettoyé par un espace
            for (int i = 0; i < NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES; i++) {

                String textDeTweetCourant = PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).getTweettext();
                for (Emoticone emoticone : lecteurFichierEmoticones.getListeDesEmoticones()) {
                    //icic le remplacemen
                    textDeTweetCourant = textDeTweetCourant.replace(emoticone.getCode(), " ");

                }
                PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).setTweettext(textDeTweetCourant);

                double progress = (double) (i) / (double) NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES;
                Platform.runLater(() -> pbSupprimerEmoticones.setProgress(progress));

                System.out.println(i );
            }
        }

        private void eliminerURL() {

            for (int i = 0; i < NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES; i++) {

                String tweetApprentissage = PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).getTweettext();

                String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";

                String newChaineCharactere = tweetApprentissage.replaceAll(urlPattern, " ");

                //mettre a jour la chaine de caractere du tweet dans la liste du pipeline
                PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).setTweettext(newChaineCharactere);

                double progress = (double) (i) / (double) NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES;
                Platform.runLater(() -> pbSupprimerLiens.setProgress(progress));

            }

        }

        private void eliminerNomUtilisateur() {

            for (int i = 0; i < NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES; i++) {
                String tweetAvecUserName = PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).getTweettext();

                String arobasRegex = "(?<=^|(?<=[^a-zA-Z0-9-_\\.]))@([A-Za-z]+[A-Za-z0-9]+)";

                String newStr = tweetAvecUserName.replaceAll(arobasRegex, " ");

                PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).setTweettext(newStr);

                double progress = (double) (i) / (double) NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES;
                Platform.runLater(() -> pbSupprimerArobas.setProgress(progress));


            }


        }

        private void eliminerPonctuation() {
            for (int i = 0; i < NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES; i++) {

                String tweetAvecPonctuation = PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).getTweettext();
                String nonAlphabetRegex = "[^A-Za-z0-9]";

                String newStr = tweetAvecPonctuation.replaceAll(nonAlphabetRegex, " ");
                PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).setTweettext(newStr);

                double progress = (double) (i) / (double) NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES;
                Platform.runLater(() -> pbSupprimerPonctuation.setProgress(progress));

            }
        }

        private void eliminerMotsVide() {

            LecteurFichierMotsVides lecteurFichierMotsVides = new LecteurFichierMotsVides();
            lecteurFichierMotsVides.recupererMotVidesAnglais();
            for (int i = 0; i < NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES; i++) {


                String tweetText = PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).getTweettext();
                for (String motvide : lecteurFichierMotsVides.getListeMotsVides()) {
                    tweetText = tweetText.replace(" " + motvide + " ", " ");
                }

                PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).setTweettext(tweetText);

                double progress = (double) (i) / (double) NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES;
                Platform.runLater(() -> pbEliminerMotVide.setProgress(progress));

            }
        }

        private void normaliser(){

            for (int i = 0; i < NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES; i++) {

                String tweetText = PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).getTweettext();
                tweetText = tweetText.toLowerCase();
                PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).setTweettext( tweetText);
                double progress = (double) i / (double) NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES;
                Platform.runLater(() -> pbNormaliserText.setProgress(progress));

            }
        }

        @Override
        protected void succeeded() {
            super.succeeded();
            showNotification("Nettoyage des tweets a été terminé avec succès !");
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