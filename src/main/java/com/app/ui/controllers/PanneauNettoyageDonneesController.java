package com.app.ui.controllers;

import com.app.helper.ErrorHelper;
import com.app.model.Emoticone;
import com.app.model.Tweet;
import com.app.temp.LecteurFichierEmoticones;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.ToggleSwitch;

import static com.app.helper.NotificationHelper.showNotification;
import static com.app.helper.Statics.NOMBRE_DE_TWEETS_SANDER;
import static com.app.helper.Statics.PIPELINE;

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
    private void demarrerNettoyage() {
        TaskNettoyeurDeTweet taskNettoyeurDeTweet = new TaskNettoyeurDeTweet();
        new Thread(taskNettoyeurDeTweet).start();
    }

    @FXML
    private void voirExempleResultat() {
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
            popOver.show(btnVoirExempleResultat);

            FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("fxml/" + PANNEAU_POPUP_EXEMPLE_NETTOYAGE));
            Parent root = (Parent) fxmlLoader.load();


            PanneauPopupExempleNettoyage panneauPopupExempleNettoyage = fxmlLoader.getController();
            panneauPopupExempleNettoyage.setTweetAvantNettoyage(tweetAvantNettoyage);
            panneauPopupExempleNettoyage.setTweetApresNettoyage(tweetApresNettoyage);

            popOver.setContentNode(root);

        } catch (Exception e) {
            ErrorHelper.showErrorDialog(e);
        }
    }

    @FXML
    private void showPopupComparaison() {
        try {

            PopOver popOver = new PopOver();
            popOver.setTitle("Comparaison");
            popOver.setHeaderAlwaysVisible(true);

            FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("fxml/" + PANNEAU_POPUP_COMPARAISON_TWEETS));
            Parent root = (Parent) fxmlLoader.load();


            PanneauPopupComparaisonListeTweetsController panneauPopupComparaisonListeTweetsController = fxmlLoader.getController();
            panneauPopupComparaisonListeTweetsController.afficherLesListesDeTweets();

            //popOver.setContentNode(root);
            //popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_CENTER);
            //popOver.show( btnVoirExempleResultat );

            Scene scene = new Scene(root);
            Stage fenetrecomparaison = new Stage();
            fenetrecomparaison.setScene(scene);
            fenetrecomparaison.setTitle("Comparaison entre les tweets originaux et les tweets néttoyés");
            fenetrecomparaison.show();


            System.out.println( "Liste des tweets originaux : ");
            System.out.println( PIPELINE.getListeDeTweetsApprentissage().toString());
            System.out.println( "Liste des tweets nettoyes : ");
            System.out.println( PIPELINE.getListeDeTweetsApprentissageNettoye().toString());

        } catch (Exception e) {
            ErrorHelper.showErrorDialog(e);
        }
    }

    //une classe qui comporte 8 methodes
    private class TaskNettoyeurDeTweet extends Task {

        @Override
        protected Object call() throws Exception {

            eleminerLesTweetsSupperflus();
            eliminerLesTweetsRepetes();
            eliminerURL();
            eliminerEmoticones();
            return null;
        }

        private void eleminerLesTweetsSupperflus() {

            for (int i = 0; i < PIPELINE.getListeDeTweetsApprentissage().size(); i++) {
                Tweet tweetdApprentissage = PIPELINE.getListeDeTweetsApprentissage().get(i);
                if (tweetdApprentissage.getLangue().equals("en")) { //si le tweet anglais
                    PIPELINE.ajouterUnTweetdApprentissageAlaListeNottoyee(tweetdApprentissage); //on l'ajout a la liste des tweets nettoyés
                }
                double progress = i * 100 / NOMBRE_DE_TWEETS_SANDER;
                Platform.runLater(() -> pbSupprimerTweetsSupperflus.setProgress(progress));
            }

            //mettre a jour le nombre de tweets de sanders
            NOMBRE_DE_TWEETS_SANDER = PIPELINE.getListeDeTweetsApprentissageNettoye().size();
        }

        private void eliminerLesTweetsRepetes() {


            for (int i = 0; i < PIPELINE.getListeDeTweetsApprentissageNettoye().size(); i++) {

                int nbrRepetitionsDuTweeti = 0; // hada un conteur de répétition  de chaque tweet donc sa place ici..
                Tweet tweetApprentissage = PIPELINE.getListeDeTweetsApprentissageNettoye().get(i); //j'ai récupéré le tweet nume i

                //si il n'existe pas dans la liste nettoyer alors ajouté sinon supprimé koi! tu recupere le men liste puis tu test si il existe plusieurs fois si o u i    tu élémine les duppliqués  hakdak le traitment ykoun grave lents kifech ndirr !! a toi de trouver l'algorithme optimal ;)ok
                for (int j = 0; j < PIPELINE.getListeDeTweetsApprentissageNettoye().size(); j++) {
                    Tweet tweetAtester = PIPELINE.getListeDeTweetsApprentissageNettoye().get(j);

                    if (tweetApprentissage.equals(tweetAtester)) {
                        nbrRepetitionsDuTweeti++;
                        if (nbrRepetitionsDuTweeti > 1) {
                            PIPELINE.getListeDeTweetsApprentissageNettoye().remove(i);
                        }
                    }

                }
                double progress = i * 100 / NOMBRE_DE_TWEETS_SANDER;
                Platform.runLater(() -> pbSupprimerTweetsDupliques.setProgress(progress));
            }

            //mettre a jour le nombre de tweets de sanders
            NOMBRE_DE_TWEETS_SANDER = PIPELINE.getListeDeTweetsApprentissageNettoye().size();


        }

        private void eliminerEmoticones() {
            // lire le fichier emoticones
            LecteurFichierEmoticones lecteurFichierEmoticones = new LecteurFichierEmoticones();
            lecteurFichierEmoticones.recupererLesEmoticones();

            //parcours de la liste nettoye

            //remplacer les emoticones dans chaque tweet nettoyé par un espace

            for (int i = 0; i < PIPELINE.getListeDeTweetsApprentissageNettoye().size(); i++) {

                String textDeTweetCourant = PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).getTweettext();
                for (Emoticone emoticone : lecteurFichierEmoticones.getListeDesEmoticones()) {
                    //icic le remplacemeny, dsl nroh w nji  okokk ?vas yy a++a ++
                    textDeTweetCourant = textDeTweetCourant.replace(emoticone.getCode(), " ");

                }
                PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).setTweettext( textDeTweetCourant );

                double progress = i * 100 / NOMBRE_DE_TWEETS_SANDER;
                Platform.runLater(() -> pbSupprimerEmoticones.setProgress(progress));
            }
        }
        private void eliminerURL(){

            for (int i = 0; i < PIPELINE.getListeDeTweetsApprentissageNettoye().size(); i++) {
             String tweetApprentissage = PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).getTweettext();
                String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";

                String newChaineCharactere = tweetApprentissage.replaceAll(urlPattern, " ");
                //mettre a jour la chaine de caractere du tweet dans la liste du pipeline
                PIPELINE.getListeDeTweetsApprentissageNettoye().get(i).setTweettext(newChaineCharactere);
                double progress = i * 100 / NOMBRE_DE_TWEETS_SANDER;
                Platform.runLater(() -> pbSupprimerLiens.setProgress(progress));

            }

            }


        @Override
        protected void succeeded() {
            super.succeeded();
            showNotification("Suppression des tweets supperflus a été terminé avec succès !");
        }
    }
}