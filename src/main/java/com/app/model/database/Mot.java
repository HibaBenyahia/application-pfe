package com.app.model.database;

/**
 * Created by Oussama on 26/05/2016.
 */
public class Mot {

    private int idMot;
    private int idCorpus;
    private String testeMot;
    private int nbrApparClassePos;
    private int nbrApparClasseNeg;
    private float probaPos;
    private float probaNeg;
    private int codeNgram;

    public Mot(int idMot, int idCorpus, String testeMot, int nbrApparClassePos, int nbrApparClasseNeg, float probaPos, float probaNeg, int codeNgram) {
        this.idMot = idMot;
        this.idCorpus = idCorpus;
        this.testeMot = testeMot;
        this.nbrApparClassePos = nbrApparClassePos;
        this.nbrApparClasseNeg = nbrApparClasseNeg;
        this.probaPos = probaPos;
        this.probaNeg = probaNeg;
        this.codeNgram = codeNgram;
    }

    public int getIdMot() {
        return idMot;
    }

    public void setIdMot(int idMot) {
        this.idMot = idMot;
    }

    public int getIdCorpus() {
        return idCorpus;
    }

    public void setIdCorpus(int idCorpus) {
        this.idCorpus = idCorpus;
    }

    public String getTesteMot() {
        return testeMot;
    }

    public void setTesteMot(String testeMot) {
        this.testeMot = testeMot;
    }

    public int getNbrApparClassePos() {
        return nbrApparClassePos;
    }

    public void setNbrApparClassePos(int nbrApparClassePos) {
        this.nbrApparClassePos = nbrApparClassePos;
    }

    public int getNbrApparClasseNeg() {
        return nbrApparClasseNeg;
    }

    public void setNbrApparClasseNeg(int nbrApparClasseNeg) {
        this.nbrApparClasseNeg = nbrApparClasseNeg;
    }

    public float getProbaPos() {
        return probaPos;
    }

    public void setProbaPos(float probaPos) {
        this.probaPos = probaPos;
    }

    public float getProbaNeg() {
        return probaNeg;
    }

    public void setProbaNeg(float probaNeg) {
        this.probaNeg = probaNeg;
    }

    public int getCodeNgram() {
        return codeNgram;
    }

    public void setCodeNgram(int codeNgram) {
        this.codeNgram = codeNgram;
    }

    @Override
    public String toString() {
        return "Mot{" +
                "idMot=" + idMot +
                ", idCorpus=" + idCorpus +
                ", testeMot='" + testeMot + '\'' +
                ", nbrApparClassePos=" + nbrApparClassePos +
                ", nbrApparClasseNeg=" + nbrApparClasseNeg +
                ", probaPos=" + probaPos +
                ", probaNeg=" + probaNeg +
                ", codeNgram=" + codeNgram +
                '}';
    }
}
