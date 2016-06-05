package com.app.ui.controllers;

import com.app.helper.ErrorHelper;
import com.app.helper.NotificationHelper;
import com.app.model.ClassificateurDeTweets;
import com.app.model.Tweet;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

import static com.app.helper.Statics.NOMBRE_DE_TWEETS_DE_TEST_NETTOYES;
import static com.app.helper.Statics.PIPELINE;

/**
 * Created by Oussama on 29/05/2016.
 */
public class PanneauTestClassificationController {
    @FXML
    private ProgressIndicator piClassification;
    @FXML
    private Button btnDemarrerClassification;
    @FXML
    private Button btnAfficherResultat;

    @FXML
    private void demarrerClassification(){
        System.out.println("Demarrer Classification");
        TaskClassifieur taskClassifieur = new TaskClassifieur();
        new Thread(taskClassifieur).start();
    }

    @FXML
    private void afficherResultat(){
        System.out.println("Afficher le Resultat");
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("fxml/panneau_liste_tweets_classes.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            PanneauListeTweetsClassesController panneauListeTweetsClassesController = fxmlLoader.getController();
            panneauListeTweetsClassesController.afficherLalisteDesTweets();

            Scene scene = new Scene(root);
            Stage fenetrecomparaison = new Stage();
            fenetrecomparaison.setScene(scene);
            fenetrecomparaison.setTitle("Résultat de classification de tweets de test");
            fenetrecomparaison.show();

        } catch (Exception e) {
            ErrorHelper.showErrorDialog(e);
        }
    }

    private class TaskClassifieur extends Task{


        @Override
        protected Object call() throws Exception {

            demmarerTestDeClassification();

            return null;
        }

        private void demmarerTestDeClassification() {

            ClassificateurDeTweets classificateurDeTweets= new ClassificateurDeTweets();

            for (int i = 0; i < NOMBRE_DE_TWEETS_DE_TEST_NETTOYES; i++) {

                Tweet tweetAClasser = PIPELINE.getListeDeTweetsDeTestNettoye().get(i);

                double prbabilitePositive= classificateurDeTweets.calculerLaProbabilitePositiveDeTweet( tweetAClasser );
                double probabiliteNeNgative = classificateurDeTweets.calculerLaProbabiliteNegativeDeTweet( tweetAClasser );

                tweetAClasser.setProbabiliteDeLaClassePositive(prbabilitePositive);
                tweetAClasser.setProbabiliteDeLaClasseNegative(probabiliteNeNgative);

                double sentimentDeTweet = classificateurDeTweets.calculerSentimentApresTest(tweetAClasser);
                tweetAClasser.setSentimentApresClassification(sentimentDeTweet);

                //hakda c bn pout cetteq qclass (y) oui doka nekhedmo les methodes^^^^oui:)
                //dkika brk yokhrjo bah na9der nahder m3ak  okk
                double progress = (double) i / (double) NOMBRE_DE_TWEETS_DE_TEST_NETTOYES;
                Platform.runLater(() -> piClassification.setProgress( progress ));


            }

        }

        @Override
        protected void succeeded() {
            super.succeeded();
            Platform.runLater(() -> piClassification.setProgress( 1.0 ));
            System.out.println( "Evaluation de précision = "+ calculerPrecisionDeClassification() );
            NotificationHelper.showNotification("Classification a été terminé avec succès !");
        }

        private double calculerPrecisionDeClassification() {
            int precision = 0;
            double resultatARetourner = 0;

            for (Tweet tweet : PIPELINE.getListeDeTweetsDeTestNettoye()) {
                if (tweet.getSentiment() == tweet.getSentimentApresClassification())
                    System.out.println( tweet.getSentiment()+"   "+tweet.getSentimentApresClassification());
                    precision ++;
            }

            resultatARetourner = (double) (precision * 100) / (double) NOMBRE_DE_TWEETS_DE_TEST_NETTOYES;
            return resultatARetourner;
        }

        @Override
        protected void failed() {
            super.failed();
            System.out.println("Failed");
        }

        @Override
        protected void cancelled() {
            super.cancelled();
            System.out.println("Canceled");
        }
    }
}
