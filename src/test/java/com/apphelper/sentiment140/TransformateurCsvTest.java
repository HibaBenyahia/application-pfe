package com.apphelper.sentiment140;

import org.junit.Test;

/**
 * Created by Oussama on 02/05/2016.
 */
public class TransformateurCsvTest {
    @Test
    public void lireEtTransformerLesTweets() throws Exception {
        TransformateurCsv transformateurCsv = new TransformateurCsv();
        transformateurCsv.lireEtTransformerLesTweets();
    }

}