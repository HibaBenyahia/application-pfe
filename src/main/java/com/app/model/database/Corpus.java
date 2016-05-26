package com.app.model.database;

/**
 * Created by Oussama on 26/05/2016.
 */
public class Corpus {

    private int idCorpus;
    private String nomCorpus;
    private int tailleVocabulaire;

    public Corpus(int idCorpus, String nomCorpus, int tailleVocabulaire) {
        this.idCorpus = idCorpus;
        this.nomCorpus = nomCorpus;
        this.tailleVocabulaire = tailleVocabulaire;
    }

    public int getIdCorpus() {
        return idCorpus;
    }

    public void setIdCorpus(int idCorpus) {
        this.idCorpus = idCorpus;
    }

    public String getNomCorpus() {
        return nomCorpus;
    }

    public void setNomCorpus(String nomCorpus) {
        this.nomCorpus = nomCorpus;
    }

    public int getTailleVocabulaire() {
        return tailleVocabulaire;
    }

    public void setTailleVocabulaire(int tailleVocabulaire) {
        this.tailleVocabulaire = tailleVocabulaire;
    }

    @Override
    public String toString() {
        return "Corpus{" +
                "idCorpus=" + idCorpus +
                ", nomCorpus='" + nomCorpus + '\'' +
                ", tailleVocabulaire=" + tailleVocabulaire +
                '}';
    }
}
