package com.app.helper;

import com.app.model.Pipeline;

/**
 * Created by Oussama on 02/05/2016.
 */
public class Statics {

    public static final String SANDERS_LEARNING_DATASET = "sanders_learning_dataset.csv";
    public static final String SENTIMENT140_LEARNING_DATASET = "sentiment140_learning_dataset.csv";

    public static final int NUMBER_OF_TWEETS_OF_SANDERS_DATASET = 4501;
    public static final int NUMBER_OF_TWEETS_OF_SENTIMENT_DATASET = 1_600_000;

    public static final Pipeline PIPELINE = new Pipeline();

}
