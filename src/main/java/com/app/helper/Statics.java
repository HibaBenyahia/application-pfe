package com.app.helper;

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

    public static int NUMBER_DE_TWEET_DE_SENTIMENT140 = 1_600_000;
    public static int NOMBRE_DE_TWEETS_SANDER = 4500;
    public static int NOMBRE_DE_TWEETS_DE_TEST = 0;

    public static final Pipeline PIPELINE = new Pipeline();
    public static ConnecteurBaseDeDonnees CONNECTEUR_BASE_DE_DONNEES;

    public static int N_GRAM_CHOISI = 1;
    public static int DATASET_CHOISI = SANDERS_DATASET;


}
