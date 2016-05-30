package com.app.model;

import com.app.model.database.ConnecteurBaseDeDonnees;
import org.junit.Before;
import org.junit.Test;

import static com.app.helper.Statics.CONNECTEUR_BASE_DE_DONNEES;

/**
 * Created by Oussama on 30/05/2016.
 */
public class ClassificateurDeTweetsTest {

    private ClassificateurDeTweets classificateurDeTweets;

    @Before
    public void creerUnClassificateurDeTweet(){
        classificateurDeTweets = new ClassificateurDeTweets();
        CONNECTEUR_BASE_DE_DONNEES = new ConnecteurBaseDeDonnees();

    }


    @Test
    public void testDeConstructeurDuClassificateur() throws Exception {
        System.out.println( CONNECTEUR_BASE_DE_DONNEES.getlisteDeClasse().toString() );

    }
}