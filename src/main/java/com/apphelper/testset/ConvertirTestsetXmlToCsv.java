package com.apphelper.testset;

import com.app.model.Tweet;
import com.apphelper.EnregistreurFichierCsv;
import com.apphelper.sanders.LecteurFichierXml;

import java.util.ArrayList;

/**
 * Created by Oussama on 04/06/2016.
 */
public class ConvertirTestsetXmlToCsv {

    public static void main(String[] args) {

        LecteurFichierXml lecteurFichierXml = new LecteurFichierXml();
        lecteurFichierXml.lireFichierDeTweet();
        ArrayList<Tweet> listeDeTweetDeTest = lecteurFichierXml.getListeDeTweet();

        EnregistreurFichierCsv enregistreurFichierCsv = new EnregistreurFichierCsv();

        for (Tweet tweet :
                listeDeTweetDeTest) {
            enregistreurFichierCsv.enregistrerUnTweetEnStreaming( tweet );
        }

        enregistreurFichierCsv.saveAndClose( "test_tweets_iphone_se.csv");
    }
}
