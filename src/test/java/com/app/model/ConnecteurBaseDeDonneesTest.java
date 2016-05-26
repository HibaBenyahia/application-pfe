package com.app.model;

import org.junit.Test;

/**
 * Created by Oussama on 26/05/2016.
 */
public class ConnecteurBaseDeDonneesTest {
    @Test
    public void testDeConnectionALaBDD() throws Exception {
        ConnecteurBaseDeDonnees connecteurBaseDeDonnees = new ConnecteurBaseDeDonnees();

    }

    @Test
    public void testAffichgeDesClasses() throws Exception {
        ConnecteurBaseDeDonnees connecteurBaseDeDonnees = new ConnecteurBaseDeDonnees();
        connecteurBaseDeDonnees.afficherLaListeDesClasse();
        connecteurBaseDeDonnees.closeConnection();
    }
}