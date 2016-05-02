package com.apphelper.sanders;

import com.app.helper.Statics;
import com.app.model.Tweet;
import com.apphelper.EnregistreurFichierCsv;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Oussama on 02/05/2016.
 */
public class EnregistreurFichierCsvTest {

    ArrayList<Tweet> listeDeTweet;
    LecteurFichierXml lecteurFichierXml;

    @Before
    public void setUp() throws Exception {
        lecteurFichierXml = new LecteurFichierXml();
        lecteurFichierXml.lireFichierDeTweet();
        listeDeTweet = lecteurFichierXml.getListeDeTweet();
        lecteurFichierXml = null;
    }

    @After
    public void tearDown() throws Exception {
        listeDeTweet = null;
    }

    @Test
    public void enregistrerUnTweetEnStreaming() throws Exception {
        Tweet tweet =  listeDeTweet.get(0);
        System.out.println( tweet.toString() );
        EnregistreurFichierCsv enregistreurFichierCsv = new EnregistreurFichierCsv();
        enregistreurFichierCsv.enregistrerUnTweetEnStreaming( tweet );
        enregistreurFichierCsv.saveAndClose( Statics.SANDERS_LEARNING_DATASET );
    }

    @Test
    public void enregistrerLaListeDesTweetsEnStreaming() throws Exception{
        EnregistreurFichierCsv enregistreurFichierCsv = new EnregistreurFichierCsv();
        for (Tweet tweet: listeDeTweet) {
            enregistreurFichierCsv.enregistrerUnTweetEnStreaming( tweet );
        }
        enregistreurFichierCsv.saveAndClose( Statics.SANDERS_LEARNING_DATASET );
    }

}