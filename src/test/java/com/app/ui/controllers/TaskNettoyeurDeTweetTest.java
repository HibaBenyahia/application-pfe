package com.app.ui.controllers;

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

}