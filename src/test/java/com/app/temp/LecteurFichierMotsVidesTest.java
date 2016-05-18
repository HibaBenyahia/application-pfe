package com.app.temp;

import org.junit.Test;

/**
 * Created by hiba on 18/05/2016.
 */
public class LecteurFichierMotsVidesTest {
    @Test
    public void recupererMotVidesAnglais() throws Exception {
        LecteurFichierMotsVides lecteurFichierMotsVides = new LecteurFichierMotsVides();
        lecteurFichierMotsVides.recupererMotVidesAnglais();
        System.out.println( lecteurFichierMotsVides.getListeMotsVides().toString());
    }

}