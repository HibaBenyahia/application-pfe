package com.app.model.database;

import com.app.helper.DirectoryHelper;
import com.app.model.CalculateurStatistiquesApprentissage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by Oussama on 26/05/2016.
 */

public class ConnecteurBaseDeDonnees {

    public static final int ID_CLASSE_SENTIMENT_POSITIF = 1;
    public static final int ID_CLASSE_SENTIMENT_NGATIF = 2;

    private Connection connection;
    private Statement statement;

    public ConnecteurBaseDeDonnees() {
        connection = connecterALaBDD();
    }

    private Connection connecterALaBDD() {
        try {

            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection( DirectoryHelper.getDatabaseFilePath() );
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

                Mot mot = creerMotFromResultatDeLaRequette( resultSet );
                listeDeMotAretourner.add(mot);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return listeDeMotAretourner;
    }

    private Mot creerMotFromResultatDeLaRequette(ResultSet resultSet) {
        Mot motResultat = null;

        try {

                int idMot = resultSet.getInt("id_mot");
                int idCorpus = resultSet.getInt("id_corpus");
                String texteMot = resultSet.getString("texte_mot");
                int nbrApparClassePos = resultSet.getInt("nbr_appr_classe_pos");
                int nbrApparClasseNeg = resultSet.getInt("nbr_appr_classe_neg");
                float probaPos = resultSet.getFloat("proba_pos");
                float probaNeg = resultSet.getFloat("proba_neg");
                int codeNgram = resultSet.getInt("code_ngram");

                motResultat = new Mot(idMot, idCorpus, texteMot, nbrApparClassePos, nbrApparClasseNeg, probaPos, probaNeg, codeNgram);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return motResultat;
    }

    //getListeDeCorpus
    public ObservableList<Corpus> getListeDeCorpus(){
        ObservableList<Corpus> listeDeCorpusAreourner = FXCollections.observableArrayList();
        ResultSet resultSet= getResultatDeLaRequete("SELECT  * FROM corpus");
        try {
            while(resultSet.next()){

                //
                Corpus corpus = creerCorpusFromResultatDeLaRequette( resultSet );
                listeDeCorpusAreourner.add(corpus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listeDeCorpusAreourner;
    }

    private Corpus creerCorpusFromResultatDeLaRequette(ResultSet resultSet) {
        Corpus corpusResultat = null;

        try {
            int idCorpus = resultSet.getInt("id_corpus");
            String nomCorpus = resultSet.getString("nom_corpus");
            int tailleVocabulaire = resultSet.getInt("taille_vocabulaire");

            corpusResultat = new Corpus(idCorpus, nomCorpus, tailleVocabulaire);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return corpusResultat;
    }

    //getListeDeClasses
    public ObservableList<Classe> getlisteDeClasse(){

        ObservableList<Classe> listeDeClasseArecuperer = FXCollections.observableArrayList();
        ResultSet resultSet= getResultatDeLaRequete("SELECT * FROM  classe");

        try {
            while(resultSet.next()){

                Classe classe = creerClasseFromResultatDeLaRequette( resultSet );
                listeDeClasseArecuperer.add(classe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeDeClasseArecuperer;
    }

    private Classe creerClasseFromResultatDeLaRequette(ResultSet resultSet) {
        Classe classeResultat = null;

        try {
            int idClasse = resultSet.getInt("id_classe");
            int idCorpus = resultSet.getInt("id_corpus");
            String nomClasse = resultSet.getString("nom_classe");
            int nbrDeMots = resultSet.getInt("nombre_de_mot");
            float prior = resultSet.getFloat("prior");

            classeResultat = new Classe(idClasse, idCorpus, nomClasse, nbrDeMots, prior);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classeResultat;
    }

    private double getTotalDuneRequeteCount(ResultSet resultSet) {
        double total = 0;

        try {
            total = resultSet.getDouble("total");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
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


    public boolean chercherMot(String gram) {
        boolean resultat = false;
        ResultSet rs = getResultatDeLaRequete("SELECT * FROM mots WHERE texte_mot ='"+gram+"' ");

        try {

            if (rs.next())
                resultat = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultat;
    }


    public void updateNombreApparitionGram(String gram, int idClasseSentiment) {

        Mot motAMettreAJour = getMotDeGram( gram );

        if ( idClasseSentiment == ID_CLASSE_SENTIMENT_POSITIF)
            mettreAJourNombreDApparitionDansLaClassePositive( motAMettreAJour );
        else
            mettreAJourNombreDApparitionDansLaClasseNegative( motAMettreAJour );

    }

    private void mettreAJourNombreDApparitionDansLaClasseNegative(Mot motAMettreAJour) {

        int nouveauNbrApparClasseNeg = motAMettreAJour.getNbrApparClasseNeg() + 1;
        String requete = "UPDATE mots SET nbr_appr_classe_neg = "+nouveauNbrApparClasseNeg+" WHERE id_mot = "+motAMettreAJour.getIdMot();

        try {
            statement.executeUpdate( requete );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void mettreAJourNombreDApparitionDansLaClassePositive(Mot motAMettreAJour) {

        int nouveauNbrApparClassePos = motAMettreAJour.getNbrApparClassePos() + 1;
        String requete = "UPDATE mots SET nbr_appr_classe_pos = "+nouveauNbrApparClassePos+" WHERE id_mot = "+motAMettreAJour.getIdMot();

        try {
            statement.executeUpdate( requete );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Mot getMotDeGram(String gram) {
        Mot motResltat = null;

        ResultSet rs = getResultatDeLaRequete(String.format("SELECT * FROM mots WHERE texte_mot = '%s'", gram));

        try {
            while (rs.next()){
               motResltat = creerMotFromResultatDeLaRequette( rs );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return motResltat;
    }

    public void ajouterUnMot(Mot mot) {


        String requete = "INSERT INTO mots (texte_mot, nbr_appr_classe_pos, nbr_appr_classe_neg, proba_pos, proba_neg, code_ngram, id_corpus)" +
                " VALUES ('"+mot.getTesteMot()+"', "+mot.getNbrApparClassePos()+", "+mot.getNbrApparClasseNeg()+", "+mot.getProbaPos()+", "+mot.getProbaNeg()+", "+mot.getCodeNgram()+", "+mot.getIdCorpus()+")";

        executerUneRequete( requete );


    }

    private void executerUneRequete(String requete) {
        try {

            statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate( requete );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLesPriorsDesClasses(CalculateurStatistiquesApprentissage calculateurStatistiquesApprentissage) {

        Classe classePositive = getClasseAvecId( 1 );
        classePositive.setPrior( calculateurStatistiquesApprentissage.getPriorClassePositive()  );
        mettreAJourUneClasse( classePositive );

        Classe classeNegative = getClasseAvecId( 2 );
        classeNegative.setPrior( calculateurStatistiquesApprentissage.getPriorClasseNegative() );
        mettreAJourUneClasse( classeNegative );


    }

    private void mettreAJourUneClasse(Classe classeAMettreAJour) {

        //language=SQLite
        String requete = "UPDATE classe SET nombre_de_mot = "+classeAMettreAJour.getNombreDeMot()+", prior = "+classeAMettreAJour.getPrior()+" WHERE id_classe = "+classeAMettreAJour.getIdClasse();

        try {
            statement.executeUpdate( requete );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Classe getClasseAvecId(int idClasse) {
        Classe classeARetourner = null;
        ResultSet resultSet = getResultatDeLaRequete("SELECT * FROM classe WHERE id_classe = "+ idClasse);

        try {
            while (resultSet.next()){
                classeARetourner = creerClasseFromResultatDeLaRequette( resultSet );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classeARetourner;
    }

    public int countNombreDeMotsClassePositive() {
        int nombreARetourner = 0;
        ResultSet resultSet= getResultatDeLaRequete("SELECT sum(nbr_appr_classe_pos) AS total FROM mots");

        try {
            while (resultSet.next()){
                nombreARetourner = (int) getTotalDuneRequeteCount( resultSet );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreARetourner;
    }

    public int countNombreDeMotsClasseNegative() {
        int nombreARetourner = 0;
        ResultSet resultSet= getResultatDeLaRequete("SELECT sum(nbr_appr_classe_neg) AS total FROM mots");

        try {
            while (resultSet.next()){
                nombreARetourner = (int) getTotalDuneRequeteCount( resultSet );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreARetourner;
    }

    public void updateLesNombresDeMotsDesClasses(CalculateurStatistiquesApprentissage calculateurStatistiquesApprentissage) {
        Classe classePositive = getClasseAvecId( 1 );
        classePositive.setNombreDeMot( calculateurStatistiquesApprentissage.getNombreDeMotDeLaClassePositive()  );
        mettreAJourUneClasse( classePositive );

        Classe classeNegative = getClasseAvecId( 2 );
        classeNegative.setNombreDeMot( calculateurStatistiquesApprentissage.getNombreDeMotDeLaClasseNegative() );
        mettreAJourUneClasse( classeNegative );
    }

    public int countTailleVocabulaire() {
        int nombreARetourner = 0;
        ResultSet resultSet = getResultatDeLaRequete("SELECT COUNT(*) AS total FROM mots");

        try {
            while (resultSet.next()){
                nombreARetourner = (int) getTotalDuneRequeteCount( resultSet );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreARetourner;
    }

    public void updateLaTailleDeVocabulaireDeCorpus(CalculateurStatistiquesApprentissage calculateurStatistiquesApprentissage) {

        Corpus corpusAMettreAJour = getCorpus(1);
        corpusAMettreAJour.setTailleVocabulaire( calculateurStatistiquesApprentissage.getTailleVocabulaire() );
        mettreAJourUnCorpus( corpusAMettreAJour );
    }

    private void mettreAJourUnCorpus(Corpus corpusAMettreAJour) {
        //language=SQLite
        String requete = "UPDATE corpus SET taille_vocabulaire = "+corpusAMettreAJour.getTailleVocabulaire()+" WHERE id_corpus = "+corpusAMettreAJour.getIdCorpus();

        try {
            statement.executeUpdate( requete );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Corpus getCorpus(int idCorpus) {
        Corpus corpusARetourner  = null;
        ResultSet resultSet = getResultatDeLaRequete("SELECT * FROM corpus WHERE id_corpus = "+ idCorpus);

        try {
            while (resultSet.next()){
                corpusARetourner = creerCorpusFromResultatDeLaRequette( resultSet );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return corpusARetourner;
    }

    public void updateLesProbabilitesDuMot(Mot mot) {
        //language=SQLite
        String requete = "UPDATE mots SET proba_pos = "+mot.getProbaPos()+", proba_neg = "+mot.getProbaNeg()+" WHERE id_mot = "+mot.getIdMot();

        try {
            statement.executeUpdate( requete );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
