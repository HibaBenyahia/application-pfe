package com.app.temp;

import com.app.model.Emoticone;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Oussama on 16/05/2016.
 */
public class LecteurFichierEmoticonesTest {
    private LecteurFichierEmoticones lecteurFichierEmoticones;

    @Before
    public void creerLObjetLecteur() throws Exception {
        lecteurFichierEmoticones = new LecteurFichierEmoticones();
    }


    @Test
    public void recupererLesEmoticones() throws Exception {
        lecteurFichierEmoticones.recupererLesEmoticones();
        System.out.println( lecteurFichierEmoticones.getListeDesEmoticones().toString());

    }

    @Test
    public void nettoyerUnTweet() throws Exception {

        lecteurFichierEmoticones.recupererLesEmoticones();

        String tweetText = "Saha :), Wechkom? cv? :'( khalitouniiii :p";
        System.out.println( "tweet avant nettoayge" + tweetText);
        for (Emoticone emoticone : lecteurFichierEmoticones.getListeDesEmoticones()) {
            tweetText = tweetText.replace( emoticone.getCode(), " ");
        }
        System.out.println("tweet apres nettoayge" + tweetText );

    }
}