package com.app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
