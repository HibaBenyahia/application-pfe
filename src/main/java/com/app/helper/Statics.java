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

    public static final String SANDERS_LEARNING_DATASET = "sanders_learning_dataset.csv";
    public static final String SENTIMENT140_LEARNING_DATASET = "sentiment140_learning_dataset.csv";
    public static final String SENTIMENT140_TEST_DATASET = "sentiment140_test_dataset.csv";

    public static int NUMBER_DE_TWEET_DE_SENTIMENT140 = 1_600_000;
    public static int NOMBRE_DE_TWEETS_SANDER = 4501;
    public static int NOMBRE_DE_TWEET_DE_TEST_SENTIMENT140 = 498;

    public static int NOMBRE_DE_TWEETS_D_APPRENTISSAGE;
    public static int NOMBRE_DE_TWEETS_D_APPRENTISSAGE_NETTOYES;
    public static int NOMBRE_DE_TWEETS_DE_TEST;
    public static int NOMBRE_DE_TWEETS_DE_TEST_NETTOYES;

    public static final Pipeline PIPELINE = new Pipeline();
    public static ConnecteurBaseDeDonnees CONNECTEUR_BASE_DE_DONNEES = new ConnecteurBaseDeDonnees();

    public static int N_GRAM_CHOISI = 1;
    public static int DATASET_CHOISI = SANDERS_DATASET;

    public static CalculateurDesStatistiques CALCULATEUR_DES_STATISTIQUES = new CalculateurDesStatistiques();


}
