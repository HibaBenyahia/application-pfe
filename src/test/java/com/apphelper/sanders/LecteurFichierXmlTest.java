package com.apphelper.sanders;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hiba on 01/05/2016.
 */
public class LecteurFichierXmlTest {

    @Test
    public void lireFichierDeTweetTest() throws Exception {
        LecteurFichierXml lecteurFichierXml = new LecteurFichierXml();
        lecteurFichierXml.lireFichierDeTweet();
        assertNotNull( lecteurFichierXml.getListeDeTweet() );
    }

    @Test
    public void testDeNombreDeTweetsLusDepuisFichierXml () throws Exception{
        LecteurFichierXml lecteurFichierXml = new LecteurFichierXml();
        lecteurFichierXml.lireFichierDeTweet();
        int nombreTweetsLus = lecteurFichierXml.getListeDeTweet().size();
        assertEquals( 4501, nombreTweetsLus);
    }

}