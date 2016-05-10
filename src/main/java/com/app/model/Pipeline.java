package com.app.model;

import javafx.collections.ObservableList;

/**
 * Created by Oussama on 08/05/2016.
 */
public class Pipeline {

    private ObservableList<Tweet> listeDeTweetsDePipeline;


    public ObservableList<Tweet> getListeDeTweetsDePipeline() {
        return listeDeTweetsDePipeline;
    }

    public void setListeDeTweetsDePipeline(ObservableList<Tweet> listeDeTweetsDePipeline) {
        this.listeDeTweetsDePipeline = listeDeTweetsDePipeline;
    }
}
