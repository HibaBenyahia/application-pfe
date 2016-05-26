package com.app.model.database;

/**
 * Created by Oussama on 26/05/2016.
 */
public class Classe {
    private int idClasse;
    private int idCorpus;
    private String nomClasse;
    private int nombreDeMot;
    private float prior;


    public Classe(int idClasse, int idCorpus, String nomClasse, int nombreDeMot, float prior) {
        this.idClasse = idClasse;
        this.idCorpus = idCorpus;
        this.nomClasse = nomClasse;
        this.nombreDeMot = nombreDeMot;
        this.prior = prior;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public int getIdCorpus() {
        return idCorpus;
    }

    public void setIdCorpus(int idCorpus) {
        this.idCorpus = idCorpus;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public int getNombreDeMot() {
        return nombreDeMot;
    }

    public void setNombreDeMot(int nombreDeMot) {
        this.nombreDeMot = nombreDeMot;
    }

    public float getPrior() {
        return prior;
    }

    public void setPrior(float prior) {
        this.prior = prior;
    }

    @Override
    public String toString() {
        return "Classe{" +
                "idClasse=" + idClasse +
                ", idCorpus=" + idCorpus +
                ", nomClasse='" + nomClasse + '\'' +
                ", nombreDeMot=" + nombreDeMot +
                ", prior=" + prior +
                '}';
    }
}
