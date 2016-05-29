package com.app.model.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.app.helper.Statics.N_GRAM_CHOISI;

/**
 * Created by Oussama on 26/05/2016.
 */
public class ConnecteurBaseDeDonneesTest {
    @Test
    public void updateLaTailleDeVocabulaireDeCorpus() throws Exception {
        System.out.println( connecteurBaseDeDonnees.getCorpus(1).toString() );
    }

    @Test
    public void countTailleVocabulaire() throws Exception {
        System.out.println( connecteurBaseDeDonnees.countTailleVocabulaire() );
    }

    @Test
    public void countNombreDeMotsClasseNegative() throws Exception {
        System.out.println( connecteurBaseDeDonnees.countNombreDeMotsClasseNegative() );
    }

    @Test
    public void countNombreDeMotsClassePositive() throws Exception {
        System.out.println( connecteurBaseDeDonnees.countNombreDeMotsClassePositive() );

    }

    @Test
    public void getClasseAvecId() throws Exception {
        System.out.println( connecteurBaseDeDonnees.getClasseAvecId(1).toString() );
    }

    @Test
    public void getMotDeGram() throws Exception {
        System.out.println( connecteurBaseDeDonnees.getMotDeGram("iphone").toString() );
    }

    @Test
    public void updateNombreApparitionGram() throws Exception {
        connecteurBaseDeDonnees.updateNombreApparitionGram("awesome", ConnecteurBaseDeDonnees.ID_CLASSE_SENTIMENT_NGATIF);
    }

    @Test
    public void chercherMot1() throws Exception {
        System.out.println( connecteurBaseDeDonnees.chercherMot("awesome"));
    }

    @Test
    public void testDeLacreationDeLaConnection() throws Exception {
        connecteurBaseDeDonnees = new ConnecteurBaseDeDonnees();

    }

    @Test
    public void ajouterUnMot() throws Exception {
        Mot mot = new Mot(-1, 1, "sweet", 1, 0, 0.0f, 0.0f, N_GRAM_CHOISI);
        connecteurBaseDeDonnees.ajouterUnMot( mot );
    }

    @Test
    public void chercherMot() throws Exception {
        System.out.println( connecteurBaseDeDonnees.chercherMot("awesome"));
    }

    private ConnecteurBaseDeDonnees connecteurBaseDeDonnees;

    @Before
    public void creerConnecteur(){
        connecteurBaseDeDonnees = new ConnecteurBaseDeDonnees();
    }

    @After
    public void closeConnection(){
        connecteurBaseDeDonnees.closeConnection();
    }

    @Test
    public void getlisteDeClasse() throws Exception {
        System.out.println( connecteurBaseDeDonnees.getlisteDeClasse().toString() );
    }

    @Test
    public void getListeDeCorpus() throws Exception {
        System.out.println( connecteurBaseDeDonnees.getListeDeCorpus().toString() );
    }

    @Test
    public void getListeDeMots() throws Exception {
        System.out.println( connecteurBaseDeDonnees.getListeDeMots().toString() );
    }

    @Test
    public void testDeConnectionALaBDD() throws Exception {
        ConnecteurBaseDeDonnees connecteurBaseDeDonnees = new ConnecteurBaseDeDonnees();

    }

    @Test
    public void testAffichgeDesClasses() throws Exception {
        connecteurBaseDeDonnees.afficherLaListeDesClasse();
    }
}