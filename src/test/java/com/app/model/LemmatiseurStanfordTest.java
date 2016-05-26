package com.app.model;

import org.junit.Test;

/**
 * Created by Oussama on 24/05/2016.
 */
public class LemmatiseurStanfordTest {
    @Test
    public void appliquerLaLemmatisationEtPosTag() throws Exception {

        String tweet = "Here's why the iPhone SE is a good idea and Apple should continue making smaller screens: onforb.es/1Q382OI pic.twitter.com/DhQbaxKpFC";
        LemmatiseurStanford lemmatiseurStanford = new LemmatiseurStanford( tweet );
        lemmatiseurStanford.appliquerLaLemmatisationEtPosTag();
        System.out.println( "lemmma : "+ lemmatiseurStanford.getListeDeLemmas().toString() );
        System.out.println( "Postag : "+ lemmatiseurStanford.getListeDePosTags().toString() );

        String tweet2 = "Hello, what's up ?";
        lemmatiseurStanford = new LemmatiseurStanford( tweet2 );
        lemmatiseurStanford.appliquerLaLemmatisationEtPosTag();
        System.out.println( lemmatiseurStanford.getListeDeLemmas().toString() );

    }

}