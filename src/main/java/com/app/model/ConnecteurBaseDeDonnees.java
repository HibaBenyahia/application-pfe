package com.app.model;

import com.app.model.database.Classe;
import com.app.model.database.Corpus;
import com.app.model.database.Mot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by Oussama on 26/05/2016.
 */

public class ConnecteurBaseDeDonnees {

    private Connection connection;
    private Statement statement;

    public ConnecteurBaseDeDonnees() {
        connection = connecterALaBDD();
    }

    private Connection connecterALaBDD() {
        try {

            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:"+this.getClass().getResource("/database/nb_resultats_apprentissage.sqlite").getPath());
            return conn;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (Exception e) {
           e.printStackTrace();
        }

    }

    //getListeDeMots
    public ObservableList<Mot> getListeDeMots(){

        ObservableList<Mot> listeDeMotAretourner = FXCollections.observableArrayList();

        ResultSet resultSet= getResultatDeLaRequete("SELECT * FROM mots");
        try {
            while (resultSet.next()){
                int idMot = resultSet.getInt("id_mot");
                int idCorpus = resultSet.getInt("id_corpus");
                String texteMot = resultSet.getString("texte_mot");
                int nbrApparClassePos = resultSet.getInt("nbr_appr_classe_pos");
                int nbrApparClasseNeg = resultSet.getInt("nbr_appr_classe_neg");
                float probaPos = resultSet.getFloat("proba_pos");
                float probaNeg = resultSet.getFloat("proba_neg");
                int codeNgram = resultSet.getInt("code_ngram");

                Mot mot= new Mot(idMot, idCorpus, texteMot, nbrApparClassePos, nbrApparClasseNeg, probaPos, probaNeg, codeNgram);
                listeDeMotAretourner.add(mot);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return listeDeMotAretourner;
    }
    //getListeDeCorpus
    public ObservableList<Corpus> getListeDeCorpus(){
        ObservableList<Corpus> listeDeCorpusAreourner = FXCollections.observableArrayList();
        ResultSet resultSet= getResultatDeLaRequete("SELECT  * FROM corpus");
        try {
            while(resultSet.next()){
                int idCorpus = resultSet.getInt("id_corpus");
                String nomCorpus = resultSet.getString("nom_corpus");
                int tailleVocabulaire = resultSet.getInt("taille_vocabulaire");

                Corpus corpus = new Corpus(idCorpus, nomCorpus, tailleVocabulaire);
                listeDeCorpusAreourner.add(corpus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listeDeCorpusAreourner;
    }
    //getListeDeClasses
    public ObservableList<Classe> getlisteDeClasse(){

        ObservableList<Classe> listeDeClasseArecuperer = FXCollections.observableArrayList();
        ResultSet resultSet= getResultatDeLaRequete("SELECT * FROM  classe");

        try {
            while(resultSet.next()){
                int idClasse = resultSet.getInt("id_classe");
                int idCorpus = resultSet.getInt("id_corpus");
                String nomClasse = resultSet.getString("nom_classe");
                int nbrDeMots = resultSet.getInt("nombre_de_mot");
                float prior = resultSet.getFloat("prior");
                Classe classe = new Classe(idClasse, idCorpus, nomClasse, nbrDeMots, prior);

                listeDeClasseArecuperer.add(classe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeDeClasseArecuperer;
    }
    public void afficherLaListeDesClasse(){

        ResultSet resultSet = getResultatDeLaRequete( "SELECT * FROM classe" );
        try {

            while (resultSet.next()){
                System.out.println( resultSet.getString("id_classe"));
                System.out.println( resultSet.getString("nombre_de_mot"));
                System.out.println( resultSet.getString("prior"));
                System.out.println( resultSet.getString("id_corpus"));
                System.out.println( resultSet.getString("nom_classe"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public ResultSet getResultatDeLaRequete( String requete){
        ResultSet resultatDeRequete = null;
        try {

            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            resultatDeRequete = statement.executeQuery( requete );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultatDeRequete;
    }


}
