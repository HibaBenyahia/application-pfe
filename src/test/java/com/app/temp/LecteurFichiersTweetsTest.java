package com.app.temp;

import org.junit.Test;

/**
 * Created by Oussama on 08/05/2016.
 */
public class LecteurFichiersTweetsTest {
    @Test
    public void recupererTweetsOldFashion() throws Exception {
        LecteurFichiersTweets lecteurFichiersTweets = new LecteurFichiersTweets();
        lecteurFichiersTweets.recupererTweetsOldFashion();
    }

    @Test
    public void recupererTweetsEnArrierePlan() throws Exception {

        LecteurFichiersTweets lecteurFichiersTweets = new LecteurFichiersTweets();
        lecteurFichiersTweets.recupererTweetsEnArrierePlan();

    }

}