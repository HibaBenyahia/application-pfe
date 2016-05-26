package com.app.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Oussama on 26/05/2016.
 */
public class ConnecteurBaseDeDonneesTest {
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