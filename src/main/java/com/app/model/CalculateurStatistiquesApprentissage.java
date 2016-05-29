package com.app.model;

import com.app.model.database.Mot;

import static com.app.helper.Statics.CONNECTEUR_BASE_DE_DONNEES;
import static com.app.helper.Statics.PIPELINE;

/**
 * Created by Oussama on 27/05/2016.
 */
public class CalculateurStatistiquesApprentissage {

    private int nombreDeTweetPositifs;
    private int nombreDeTweetNegatifs;
    private int nombreDeTweetsPosEtNeg;

    private int nombreDeMotDeLaClassePositive;
    private int nombreDeMotDeLaClasseNegative;
    private int tailleVocabulaire;

    private float priorClassePositive;
    private float priorClasseNegative;

    public void calculerLesPriors(){
        calculerNombreDeTweetPositifs();
        calculerNombreDeTweetNegatifs();
        calculerNombreDeTweetPosEtNeg();
        calculerPrioirClassePositive();
        calculerPrioirClasseNegative();
    }

    public void calculerLesNombreDeMots(){
        caculerNombreDeMotDeLaClassePositive();
        caculerNombreDeMotDeLaClasseNegative();

    }

    public float getProbabilitePositiveDuMot(Mot mot){
        float resultatARetourner = 0;

        resultatARetourner = (float) (mot.getNbrApparClassePos() + 1) / (float) ( nombreDeMotDeLaClassePositive + tailleVocabulaire );

        return resultatARetourner;

    }

    public float getProbabiliteNegativeDuMot(Mot mot){
        float resultatARetourner = 0;

        resultatARetourner = (float) (mot.getNbrApparClasseNeg() + 1) / (float) ( nombreDeMotDeLaClasseNegative + tailleVocabulaire );

        return resultatARetourner;

    }

    private void caculerNombreDeMotDeLaClasseNegative() {
        nombreDeMotDeLaClasseNegative = CONNECTEUR_BASE_DE_DONNEES.countNombreDeMotsClasseNegative();
    }

    private void caculerNombreDeMotDeLaClassePositive() {
        nombreDeMotDeLaClassePositive = CONNECTEUR_BASE_DE_DONNEES.countNombreDeMotsClassePositive();
    }


    private void calculerPrioirClasseNegative() {
        priorClasseNegative = (float) nombreDeTweetNegatifs / (float) nombreDeTweetsPosEtNeg;
    }

    private void calculerPrioirClassePositive() {
        priorClassePositive = (float) nombreDeTweetPositifs / (float) nombreDeTweetsPosEtNeg;
    }

    private void calculerNombreDeTweetPosEtNeg() {
        nombreDeTweetsPosEtNeg = nombreDeTweetPositifs + nombreDeTweetNegatifs;
    }

    private void calculerNombreDeTweetPositifs() {
        nombreDeTweetPositifs = 0;
        PIPELINE.getListeDeTweetsApprentissageNettoye().stream().filter(tweet -> tweet.getSentiment() == 1.0).forEach(tweet -> nombreDeTweetPositifs++);
    }

    private void calculerNombreDeTweetNegatifs(){
        nombreDeTweetNegatifs = 0;
        PIPELINE.getListeDeTweetsApprentissageNettoye().stream().filter(tweet -> tweet.getSentiment() == -1.0d).forEach(tweet -> nombreDeTweetNegatifs++);
    }

    public int getNombreDeTweetPositifs() {
        return nombreDeTweetPositifs;
    }

    public void setNombreDeTweetPositifs(int nombreDeTweetPositifs) {
        this.nombreDeTweetPositifs = nombreDeTweetPositifs;
    }

    public int getNombreDeTweetNegatifs() {
        return nombreDeTweetNegatifs;
    }

    public void setNombreDeTweetNegatifs(int nombreDeTweetNegatifs) {
        this.nombreDeTweetNegatifs = nombreDeTweetNegatifs;
    }

    public int getNombreDeTweetsPosEtNeg() {
        return nombreDeTweetsPosEtNeg;
    }

    public void setNombreDeTweetsPosEtNeg(int nombreDeTweetsPosEtNeg) {
        this.nombreDeTweetsPosEtNeg = nombreDeTweetsPosEtNeg;
    }

    public int getNombreDeMotDeLaClassePositive() {
        return nombreDeMotDeLaClassePositive;
    }

    public void setNombreDeMotDeLaClassePositive(int nombreDeMotDeLaClassePositive) {
        this.nombreDeMotDeLaClassePositive = nombreDeMotDeLaClassePositive;
    }

    public int getNombreDeMotDeLaClasseNegative() {
        return nombreDeMotDeLaClasseNegative;
    }

    public void setNombreDeMotDeLaClasseNegative(int nombreDeMotDeLaClasseNegative) {
        this.nombreDeMotDeLaClasseNegative = nombreDeMotDeLaClasseNegative;
    }

    public int getTailleVocabulaire() {
        return tailleVocabulaire;
    }

    public void setTailleVocabulaire(int tailleVocabulaire) {
        this.tailleVocabulaire = tailleVocabulaire;
    }

    public float getPriorClassePositive() {
        return priorClassePositive;
    }

    public void setPriorClassePositive(float priorClassePositive) {
        this.priorClassePositive = priorClassePositive;
    }

    public float getPriorClasseNegative() {
        return priorClasseNegative;
    }

    public void setPriorClasseNegative(float priorClasseNegative) {
        this.priorClasseNegative = priorClasseNegative;
    }

    public void calculerLaTailleDeVocabulaire() {
        tailleVocabulaire = CONNECTEUR_BASE_DE_DONNEES.countTailleVocabulaire();
    }
}
