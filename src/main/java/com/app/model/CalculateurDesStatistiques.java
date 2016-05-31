package com.app.model;

import static com.app.helper.Statics.PIPELINE;

/**
 * Created by Oussama on 31/05/2016.
 */

public class CalculateurDesStatistiques {

    private int nombreDeTweetsDApprentissageAvantNettoyage;
    private int nombreDeTweetsDApprentissageApresNettoyage;
    private int nombreDeTweetsDApprentissagePositif;
    private int nombreDeTweetsDApprentissageNegatif;
    private int nombreDeTweetsDApprentissageDAutresSentiments;

    private int nombreDeTweetDeTestPositifs;
    private int nombreDeTweetDeTestNegatifs;
    private int nombreDeTweetDeTestAutreSentiment;

    private int truePositives;
    private int trueNegatives;
    private int falsePositives;
    private int falseNegatives;

    private double precision;
    private double recall;
    private double fMesure;

    public void calculerBenchmark(){

        this.precision =  (double) truePositives/ (double) (truePositives + falsePositives);
        this.recall = (double) truePositives / (double) (truePositives + falseNegatives);
        this.fMesure = 2.0d * precision * recall / (precision + recall);

    }

    public void calculerLesStatistiquesSurLesDataSetDApprentissage(){

        this.nombreDeTweetsDApprentissageAvantNettoyage = PIPELINE.getListeDeTweetsApprentissage().size();
        this.nombreDeTweetsDApprentissageApresNettoyage = PIPELINE.getListeDeTweetsApprentissageNettoye().size();

        calculerNombreDeTweetsPosNegAutre();
    }


    public void calculerLesStatistiquesdeLaPhaseDeTest(){

        this.truePositives = calculerTruePositives();
        this.trueNegatives = calculerTrueNegatives();
        this.falsePositives = calculerFalsePositives();
        this.falseNegatives = calculerFalseNegatives();

        calculerLeNombreDeTweetsDeTest();
    }

    private void calculerLeNombreDeTweetsDeTest() {

        nombreDeTweetDeTestPositifs = 0;
        nombreDeTweetDeTestNegatifs = 0;
        nombreDeTweetDeTestAutreSentiment = 0;

        for (Tweet tweetDeTest :
                PIPELINE.getListeDeTweetsDeTestNettoye()) {
            if (tweetDeTest.getSentiment() == 1.0)
                nombreDeTweetDeTestPositifs ++;
            else if (tweetDeTest.getSentiment() == -1.0 )
                nombreDeTweetDeTestNegatifs ++;
            else
                nombreDeTweetDeTestAutreSentiment ++;
        }
    }

    private int calculerTruePositives() {

        int resultat = 0;
        for (Tweet tweet: PIPELINE.getListeDeTweetsDeTestNettoye() ) {

            if ( (tweet.getSentiment()==1.0) && (tweet.getSentimentApresClassification() == 1.0) ){
                resultat ++;
            }

        }
        return resultat;
    }

    private int calculerTrueNegatives() {

        int resultat = 0;
        for (Tweet tweet: PIPELINE.getListeDeTweetsDeTestNettoye() ) {

            if ( (tweet.getSentiment()== -1.0) && (tweet.getSentimentApresClassification() == -1.0) ){
                resultat ++;
            }

        }
        return resultat;
    }

    private int calculerFalsePositives() {

        int resultat = 0;
        for (Tweet tweet: PIPELINE.getListeDeTweetsDeTestNettoye() ) {

            if ( (tweet.getSentiment()== -1.0) && (tweet.getSentimentApresClassification() == 1.0) ){
                resultat ++;
            }

        }
        return resultat;
    }

    private int calculerFalseNegatives() {

        int resultat = 0;
        for (Tweet tweet: PIPELINE.getListeDeTweetsDeTestNettoye() ) {

            if ( (tweet.getSentiment()== 1.0) && (tweet.getSentimentApresClassification() == -1.0) ){
                resultat ++;
            }

        }
        return resultat;
    }

    private void calculerNombreDeTweetsPosNegAutre() {

        nombreDeTweetsDApprentissagePositif = 0;
        nombreDeTweetsDApprentissageNegatif = 0;
        nombreDeTweetsDApprentissageDAutresSentiments = 0;

        for (Tweet tweetApprentissage : PIPELINE.getListeDeTweetsApprentissage()) {
            if (tweetApprentissage.getSentiment() == 1)
                nombreDeTweetsDApprentissagePositif ++;

            else if (tweetApprentissage.getSentiment() == -1)
                nombreDeTweetsDApprentissageNegatif ++;

            else
                nombreDeTweetsDApprentissageDAutresSentiments ++;

        }
    }


    public int getNombreDeTweetsDApprentissageAvantNettoyage() {
        return nombreDeTweetsDApprentissageAvantNettoyage;
    }

    public void setNombreDeTweetsDApprentissageAvantNettoyage(int nombreDeTweetsDApprentissageAvantNettoyage) {
        this.nombreDeTweetsDApprentissageAvantNettoyage = nombreDeTweetsDApprentissageAvantNettoyage;
    }

    public int getNombreDeTweetsDApprentissageApresNettoyage() {
        return nombreDeTweetsDApprentissageApresNettoyage;
    }

    public void setNombreDeTweetsDApprentissageApresNettoyage(int nombreDeTweetsDApprentissageApresNettoyage) {
        this.nombreDeTweetsDApprentissageApresNettoyage = nombreDeTweetsDApprentissageApresNettoyage;
    }

    public int getNombreDeTweetsDApprentissagePositif() {
        return nombreDeTweetsDApprentissagePositif;
    }

    public void setNombreDeTweetsDApprentissagePositif(int nombreDeTweetsDApprentissagePositif) {
        this.nombreDeTweetsDApprentissagePositif = nombreDeTweetsDApprentissagePositif;
    }

    public int getNombreDeTweetsDApprentissageNegatif() {
        return nombreDeTweetsDApprentissageNegatif;
    }

    public void setNombreDeTweetsDApprentissageNegatif(int nombreDeTweetsDApprentissageNegatif) {
        this.nombreDeTweetsDApprentissageNegatif = nombreDeTweetsDApprentissageNegatif;
    }

    public int getNombreDeTweetsDApprentissageDAutresSentiments() {
        return nombreDeTweetsDApprentissageDAutresSentiments;
    }

    public void setNombreDeTweetsDApprentissageDAutresSentiments(int nombreDeTweetsDApprentissageDAutresSentiments) {
        this.nombreDeTweetsDApprentissageDAutresSentiments = nombreDeTweetsDApprentissageDAutresSentiments;
    }

    public int getTruePositives() {
        return truePositives;
    }

    public void setTruePositives(int truePositives) {
        this.truePositives = truePositives;
    }

    public int getTrueNegatives() {
        return trueNegatives;
    }

    public void setTrueNegatives(int trueNegatives) {
        this.trueNegatives = trueNegatives;
    }

    public int getFalsePositives() {
        return falsePositives;
    }

    public void setFalsePositives(int falsePositives) {
        this.falsePositives = falsePositives;
    }

    public int getFalseNegatives() {
        return falseNegatives;
    }

    public void setFalseNegatives(int falseNegatives) {
        this.falseNegatives = falseNegatives;
    }

    public int getNombreDeTweetDeTestPositifs() {
        return nombreDeTweetDeTestPositifs;
    }

    public void setNombreDeTweetDeTestPositifs(int nombreDeTweetDeTestPositifs) {
        this.nombreDeTweetDeTestPositifs = nombreDeTweetDeTestPositifs;
    }

    public int getNombreDeTweetDeTestNegatifs() {
        return nombreDeTweetDeTestNegatifs;
    }

    public void setNombreDeTweetDeTestNegatifs(int nombreDeTweetDeTestNegatifs) {
        this.nombreDeTweetDeTestNegatifs = nombreDeTweetDeTestNegatifs;
    }

    public int getNombreDeTweetDeTestAutreSentiment() {
        return nombreDeTweetDeTestAutreSentiment;
    }

    public void setNombreDeTweetDeTestAutreSentiment(int nombreDeTweetDeTestAutreSentiment) {
        this.nombreDeTweetDeTestAutreSentiment = nombreDeTweetDeTestAutreSentiment;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getRecall() {
        return recall;
    }

    public void setRecall(double recall) {
        this.recall = recall;
    }

    public double getfMesure() {
        return fMesure;
    }

    public void setfMesure(double fMesure) {
        this.fMesure = fMesure;
    }
}
