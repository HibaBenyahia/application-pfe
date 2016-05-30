package com.app.model;

import com.app.model.database.Mot;

import static com.app.helper.Statics.CONNECTEUR_BASE_DE_DONNEES;

/**
 * Created by Oussama on 30/05/2016.
 */
public class
ClassificateurDeTweets {

    private double priorClassePositive;
    private double priorClasseNegative;

    private final int ID_CLASSE_POSITIVE = 1;
    private final int ID_CLASSE_NEGATIVE = 2;
    private final int ID_CORPUS_SENDERS = 1;

    private final int nombreDeMotsDeLaClassePositive;
    private final int nombreDeMotsDeLaClasseNegative;
    private final int tailleDeVocabulaire;

    public ClassificateurDeTweets() {
        this.priorClassePositive= CONNECTEUR_BASE_DE_DONNEES.getClasseAvecId( ID_CLASSE_POSITIVE ).getPrior();
        this.priorClasseNegative= CONNECTEUR_BASE_DE_DONNEES.getClasseAvecId( ID_CLASSE_NEGATIVE ).getPrior();

        this.nombreDeMotsDeLaClassePositive = CONNECTEUR_BASE_DE_DONNEES.getClasseAvecId( ID_CLASSE_POSITIVE ).getNombreDeMot();
        this.nombreDeMotsDeLaClasseNegative = CONNECTEUR_BASE_DE_DONNEES.getClasseAvecId( ID_CLASSE_NEGATIVE ).getNombreDeMot();
        this.tailleDeVocabulaire = CONNECTEUR_BASE_DE_DONNEES.getCorpus( ID_CORPUS_SENDERS ).getTailleVocabulaire();

    }

    public double getPriorClassePositive() {
        return priorClassePositive;
    }

    public void setPriorClassePositive(double priorClassePositive) {
        this.priorClassePositive = priorClassePositive;
    }

    public double getPriorClasseNegative() {
        return priorClasseNegative;
    }

    public void setPriorClasseNegative(double priorClasseNegative) {
        this.priorClasseNegative = priorClasseNegative;
    }

    public double calculerLaProbabilitePositiveDeTweet(Tweet tweetAClasser) {

        //pp = priorPos * (prioPosChaqueMot)
        double probabiltePositiveDeTweet = priorClassePositive;

        for (String gram : tweetAClasser.getListOfLemmasUnGram()) { //tweetAClasser : [I, want, say, iphone]
            Mot mot = CONNECTEUR_BASE_DE_DONNEES.getMotDeGram( gram );
            if (mot == null){
                probabiltePositiveDeTweet = probabiltePositiveDeTweet * ( 1.0d / (double) (nombreDeMotsDeLaClassePositive + tailleDeVocabulaire));
            }else
                probabiltePositiveDeTweet = probabiltePositiveDeTweet * mot.getProbaPos();
        }

        return probabiltePositiveDeTweet;
    }

    public double calculerLaProbabiliteNegativeDeTweet(Tweet tweetAClasser) {
        double probabiliteNegativeDeTweet = priorClasseNegative;

        for ( String gram : tweetAClasser.getListOfLemmasUnGram()){

            Mot mot = CONNECTEUR_BASE_DE_DONNEES.getMotDeGram( gram );
            if (mot == null){
                probabiliteNegativeDeTweet = probabiliteNegativeDeTweet * (1.0d / (double) (nombreDeMotsDeLaClasseNegative + tailleDeVocabulaire));
            }else
                probabiliteNegativeDeTweet = probabiliteNegativeDeTweet * mot.getProbaNeg();
        }

        return probabiliteNegativeDeTweet;
    }


    public double calculerSentimentApresTest(Tweet tweetAClasser) {

        if (tweetAClasser.getProbabiliteDeLaClasseNegative() < tweetAClasser.getProbabiliteDeLaClassePositive())
            return 1.0;
        else
            return -1.0;
    }
}
