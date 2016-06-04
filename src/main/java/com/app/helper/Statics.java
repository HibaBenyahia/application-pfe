package com.app.helper;

import com.app.model.CalculateurDesStatistiques;
import com.app.model.Pipeline;
import com.app.model.database.ConnecteurBaseDeDonnees;

/**
 * Created by Oussama on 02/05/2016.
 */
public class Statics {

    public static int SANDERS_DATASET = 1;
    public static int SENTIMENT140_DATASET = 2;
    public static int DATASET_CHOISI = SANDERS_DATASET;

    public static int SANDERS_TESTSET = 1;
    public static int SENTIMENT140_TESTSET = 2;
    public static int IPOHONE_SE_TESTSET = 3;
    public static int TESTSET_CHOISI = SENTIMENT140_TESTSET;

    public static final String SANDERS_LEARNING_DATASET = "sanders_learning_dataset.csv";
    public static final String SENTIMENT140_LEARNING_DATASET = "sentiment140_learning_dataset.csv";
    public static final String SENTIMENT140_TEST_DATASET = "sentiment140_test_dataset.csv";

    public static int NUMBER_DE_TWEET_DE_SENTIMENT140 = 1_600_000;
    public static int NOMBRE_DE_TWEETS_SANDER = 4501;
    public static int NOMBRE_DE_TWEET_DE_TEST_SENTIMENT140 = 498;
    public static int NOMBRE_DE_TWEET_DE_TEST_IPHONE_SE = 2470;

    public static int LEMMATISATION_SEULE = 1;
    public static int LEMMATISATION_AND_POS = 2;
    public static int LEMMA_CHOISI = LEMMATISATION_SEULE;


    public static int UN_GRAM = 1;
    public static int BI_GRAM = 2;
    public static int TRI_GRAM = 3;
    public static int N_GRAM_CHOISI = UN_GRAM;



    public static int NOMBRE_DE_TWEETS_D_APPRENTISSAGE;
    public static int NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES;
    public static int NOMBRE_DE_TWEETS_DE_TEST;
    public static int NOMBRE_DE_TWEETS_DE_TEST_NETTOYES;

    public static final Pipeline PIPELINE = new Pipeline();
    public static ConnecteurBaseDeDonnees CONNECTEUR_BASE_DE_DONNEES = new ConnecteurBaseDeDonnees();


    public static CalculateurDesStatistiques CALCULATEUR_DES_STATISTIQUES = new CalculateurDesStatistiques();


}
