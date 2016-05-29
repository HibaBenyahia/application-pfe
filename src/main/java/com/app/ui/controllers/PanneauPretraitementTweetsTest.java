package com.app.ui.controllers;

import com.app.helper.FileHelper;
import com.app.helper.NotificationHelper;
import com.app.helper.Statics;
import com.app.model.DiviseurNGram;
import com.app.model.Emoticone;
import com.app.model.LemmatiseurStanford;
import com.app.model.Tweet;
import com.app.temp.LecteurFichierEmoticones;
import com.app.temp.LecteurFichierMotsVides;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

import static com.app.helper.Statics.NOMBRE_DE_TWEETS_DE_TEST;
import static com.app.helper.Statics.NOMBRE_DE_TWEETS_SANDER;
import static com.app.helper.Statics.PIPELINE;

/**
 * Created by Oussama on 29/05/2016.
 */
public class PanneauPretraitementTweetsTest {

    @FXML
    private ProgressBar pbChargementTweetsTest;
    @FXML
    private ProgressBar pbNettoyageTweetsTest;
    @FXML
    private ProgressBar pbExtractionCaracteristiqueTweetsTest;
    @FXML
    private Button btnDemarrerPreTraitement;
    @FXML
    private Button btnAfficherResultat;

    @FXML
    private void demarrerPreTraitement(){
        System.out.println("Demarrer pretraitement");
        TaskTraiteurDeTweetDeTest taskTraiteurDeTweetDeTest = new TaskTraiteurDeTweetDeTest();
        new Thread( taskTraiteurDeTweetDeTest ).start();
    }

    @FXML
    private void afficherResultat(){
        System.out.println("Affichage resultat");
    }

    private class TaskTraiteurDeTweetDeTest extends Task{

        @Override
        protected Object call() throws Exception {

            chargerLesTweetsDeTest();
            nettoyerLesTweetsDeTest();
            extraireLesCaracteristiquesDeTweetDeTest();


            return null;
        }

        private void extraireLesCaracteristiquesDeTweetDeTest() {

            Platform.runLater(() -> pbExtractionCaracteristiqueTweetsTest.setProgress( -1.0f ));

            extraireLemmatisation();
            extraireNGram();

            Platform.runLater(() -> pbExtractionCaracteristiqueTweetsTest.setProgress( 1.0f ));
        }

        private void extraireNGram() {
            DiviseurNGram diviseurNGram = new DiviseurNGram();

            for (int i = 0; i < PIPELINE.getListeDeTweetsDeTestNettoye().size(); i++) {

                Tweet tweet = PIPELINE.getListeDeTweetsDeTestNettoye().get(i);

                diviseurNGram.setTweet( tweet );

                tweet.setListOfLemmasBiGram( diviseurNGram.getListOfBigrams(tweet) );
                tweet.setListOfLemmasTriGram( diviseurNGram.getListOfTrigrams(tweet) );

            }
        }

        private void extraireLemmatisation() {
            LemmatiseurStanford lemmatiseurStanford;

            for (int i = 0; i < PIPELINE.getListeDeTweetsDeTestNettoye().size(); i++) {

                Tweet tweet = PIPELINE.getListeDeTweetsDeTestNettoye().get(i);

                lemmatiseurStanford = new LemmatiseurStanford( tweet.getTweettext() );
                lemmatiseurStanford.appliquerLaLemmatisationEtPosTag();

                tweet.setListOfLemmasUnGram( lemmatiseurStanford.getListeDeLemmas() );
                tweet.setListOfPosTags( lemmatiseurStanford.getListeDePosTags() );

            }
        }

        private void nettoyerLesTweetsDeTest() {

            Platform.runLater(() -> pbNettoyageTweetsTest.setProgress( -1.0f ));
            eleminerLesTweetsSupperflus();
            eliminerLesTweetsRepetes();
            eliminerURL();
            eliminerNomUtilisateur();
            eliminerEmoticones();
            normaliser();
            eliminerMotsVide();
            eliminerPonctuation();
            Platform.runLater(() -> pbNettoyageTweetsTest.setProgress( 1.0f ));


        }

        private void eliminerPonctuation() {
            for (int i = 0; i < PIPELINE.getListeDeTweetsDeTestNettoye().size(); i++) {

                String tweetAvecPonctuation = PIPELINE.getListeDeTweetsDeTestNettoye().get(i).getTweettext();
                String nonAlphabetRegex = "[^A-Za-z0-9]";

                String newStr = tweetAvecPonctuation.replaceAll(nonAlphabetRegex, " ");
                PIPELINE.getListeDeTweetsDeTestNettoye().get(i).setTweettext(newStr);

            }
        }

        private void eliminerMotsVide() {
            LecteurFichierMotsVides lecteurFichierMotsVides = new LecteurFichierMotsVides();
            lecteurFichierMotsVides.recupererMotVidesAnglais();
            for (int i = 0; i < PIPELINE.getListeDeTweetsDeTestNettoye().size(); i++) {


                String tweetText = PIPELINE.getListeDeTweetsDeTestNettoye().get(i).getTweettext();
                for (String motvide : lecteurFichierMotsVides.getListeMotsVides()) {
                    tweetText = tweetText.replace(" " + motvide + " ", " ");
                }

                PIPELINE.getListeDeTweetsDeTestNettoye().get(i).setTweettext(tweetText);

            }
        }

        private void normaliser() {
            for (int i = 0; i < PIPELINE.getListeDeTweetsDeTestNettoye().size(); i++) {
                String tweetText = PIPELINE.getListeDeTweetsDeTestNettoye().get(i).getTweettext();
                tweetText = tweetText.toLowerCase();
                PIPELINE.getListeDeTweetsDeTestNettoye().get(i).setTweettext( tweetText);

            }
        }

        private void eliminerEmoticones() {
            // lire le fichier emoticones
            LecteurFichierEmoticones lecteurFichierEmoticones = new LecteurFichierEmoticones();
            lecteurFichierEmoticones.recupererLesEmoticones();

            //parcours de la liste nettoye
            //remplacer les emoticones dans chaque tweet nettoyé par un espace
            for (int i = 0; i < PIPELINE.getListeDeTweetsDeTestNettoye().size(); i++) {

                String textDeTweetCourant = PIPELINE.getListeDeTweetsDeTestNettoye().get(i).getTweettext();
                for (Emoticone emoticone : lecteurFichierEmoticones.getListeDesEmoticones()) {
                    //icic le remplacemen
                    textDeTweetCourant = textDeTweetCourant.replace(emoticone.getCode(), " ");

                }
                PIPELINE.getListeDeTweetsDeTestNettoye().get(i).setTweettext(textDeTweetCourant);
            }
        }

        private void eliminerNomUtilisateur() {
            for (int i = 0; i < PIPELINE.getListeDeTweetsDeTestNettoye().size(); i++) {
                String tweetAvecUserName = PIPELINE.getListeDeTweetsDeTestNettoye().get(i).getTweettext();

                String arobasRegex = "(?<=^|(?<=[^a-zA-Z0-9-_\\.]))@([A-Za-z]+[A-Za-z0-9]+)";

                String newStr = tweetAvecUserName.replaceAll(arobasRegex, " ");

                PIPELINE.getListeDeTweetsDeTestNettoye().get(i).setTweettext(newStr);

            }
        }

        private void eliminerURL() {
            for (int i = 0; i < PIPELINE.getListeDeTweetsDeTestNettoye().size(); i++) {

                String tweetDeTest = PIPELINE.getListeDeTweetsDeTestNettoye().get(i).getTweettext();

                String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";

                String newChaineCharactere = tweetDeTest.replaceAll(urlPattern, " ");

                //mettre a jour la chaine de caractere du tweet dans la liste du pipeline
                PIPELINE.getListeDeTweetsDeTestNettoye().get(i).setTweettext(newChaineCharactere);

            }
        }

        private void eliminerLesTweetsRepetes() {
            for (int i = 0; i < PIPELINE.getListeDeTweetsDeTestNettoye().size(); i++) {

                int nbrRepetitionsDuTweeti = 0; //un conteur de répétition  de chaque tweet donc sa place ici..
                Tweet tweetDeTest = PIPELINE.getListeDeTweetsDeTestNettoye().get(i); //j'ai récupéré le tweet nume i

                //si il n'existe pas dans la liste nettoyer alors ajouté sinon supprimé
                for (int j = 0; j < PIPELINE.getListeDeTweetsDeTestNettoye().size(); j++) {
                    Tweet tweetAtester = PIPELINE.getListeDeTweetsDeTestNettoye().get(j);

                    if (tweetDeTest.equals(tweetAtester)) {
                        nbrRepetitionsDuTweeti++;
                        if (nbrRepetitionsDuTweeti > 1) {
                            PIPELINE.getListeDeTweetsDeTestNettoye().remove(i);
                        }
                    }

                }
            }

            //mettre a jour le nombre de tweets de sanders
            NOMBRE_DE_TWEETS_DE_TEST = PIPELINE.getListeDeTweetsDeTestNettoye().size();
        }

        private void eleminerLesTweetsSupperflus() {
            for (int i = 0; i < PIPELINE.getListeDeTweetsDeTest().size(); i++) {

                Tweet tweetdeTest = new Tweet(PIPELINE.getListeDeTweetsDeTest().get(i));

                if (tweetdeTest.getLangue().equals("en")) { //si le tweet anglais
                    PIPELINE.ajouterUnTweetdeTestAlaListeNottoyee(tweetdeTest); //on l'ajout a la liste des tweets nettoyés
                }

            }

        }

        private void chargerLesTweetsDeTest() {
            //lire fichier CSV en Streaming (3 instructions)
            CsvParserSettings settings = new CsvParserSettings();
            CsvParser csvParser = new CsvParser(settings);
            csvParser.beginParsing(FileHelper.getReader(ClassLoader.getSystemClassLoader().getResource("datasets/sanders_learning_dataset.csv").getPath()));

            NOMBRE_DE_TWEETS_DE_TEST = NOMBRE_DE_TWEETS_SANDER; //TODO nbt de tweet de test depend au dataset choisi

            //construire la liste des tweets
            int numeroTweet = 0;
            String[] ligneCsv;
            while ((ligneCsv = csvParser.parseNext()) != null) {

                String idTweet = ligneCsv[0];
                String user = ligneCsv[1];
                String tweettext = ligneCsv[2];
                String langue = ligneCsv[7];
                double sentiment = Double.parseDouble(ligneCsv[10]);

                Tweet tweet = new Tweet(idTweet, user, tweettext, langue, sentiment);

                Statics.PIPELINE.ajouterUnTweetdDeTest(tweet);

                numeroTweet++;
                double progress = numeroTweet * 100 / NOMBRE_DE_TWEETS_DE_TEST;
                Platform.runLater(() -> pbChargementTweetsTest.setProgress(progress));

            }
        }

        @Override
        protected void succeeded() {
            super.succeeded();
            NotificationHelper.showNotification("Prétraitement de tweets de test a été terminé avec succès");
            System.out.println( PIPELINE.getListeDeTweetsDeTestNettoye().size());
            System.out.println( PIPELINE.getListeDeTweetsDeTestNettoye().get(1).toString());
        }
    }
}
