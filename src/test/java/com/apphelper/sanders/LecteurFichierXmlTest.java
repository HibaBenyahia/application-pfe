package com.apphelper.sanders;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by hiba on 01/05/2016.
 */
public class LecteurFichierXmlTest {
    @Test
    public void lireFichierDeTweetTest() throws Exception {
        System.out.println( ClassLoader.getSystemClassLoader().getResource("sanders_dataset_tweets_sentiment.xml").getPath() );
        LecteurFichierXml lecteurFichierXml = new LecteurFichierXml();
        lecteurFichierXml.lireFichierDeTweet();
        System.out.println(lecteurFichierXml);
    }

}