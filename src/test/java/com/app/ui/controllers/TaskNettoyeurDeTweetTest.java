package com.app.ui.controllers;

import com.app.temp.LecteurFichierMotsVides;
import org.junit.Test;

/**
 * Created by hiba on 17/05/2016.
 */
public class TaskNettoyeurDeTweetTest {
    @Test
    public void testerEliminationLien(){

        String chaineCharactere = "ana samet :p http://google.com ";

        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";

        String newChaineCharactere = chaineCharactere.replaceAll(urlPattern, " ");
        System.out.println( newChaineCharactere );
    }

    @Test
    public void testerEliminationArobas() throws Exception {

        String oldStr = "well @kikooo, i'hink you'r right ! #apple";
        /* language=RegExp */
        String arobasRegex = "(?<=^|(?<=[^a-zA-Z0-9-_\\.]))@([A-Za-z]+[A-Za-z0-9]+)";

        String newStr = oldStr.replaceAll( arobasRegex, " " );
        System.out.println( newStr);

    }

    @Test
    public void testerEleminationCaractereNonAlphabetique() throws Exception {

        String oldStr = "well @kikooo, i'hink you'r right ! #apple";
        /* language=RegExp */
        String nonAlphabetRegex = "[^A-Za-z0-9]";

        String newStr = oldStr.replaceAll( nonAlphabetRegex, " " );
        System.out.println( newStr);

    }

    @Test
    public void testerEleminationMotVidesAnglais() throws Exception {

        LecteurFichierMotsVides lecteurFichierMotsVides = new LecteurFichierMotsVides();
        lecteurFichierMotsVides.recupererMotVidesAnglais();

        String tweetText = "I think that i'm in a good health. lhmdlh";
        for (String motvide : lecteurFichierMotsVides.getListeMotsVides()) {
            tweetText = tweetText.replace(" "+motvide+" ", " ");
        }
        System.out.println( tweetText );

    }

    @Test
    public void testerNormalisation() throws Exception{
        String tweetText = "I think that i'm in a good health. lhmdlh";
        tweetText = tweetText.toLowerCase();
        System.out.println( tweetText );
    }
}