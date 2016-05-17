package com.app.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by hiba on 08/05/2016.
 */
public class Pipeline {

    private ObservableList<Tweet> listeDeTweetsApprentissage;
    private ObservableList<Tweet> listeDeTweetsApprentissageNettoye;

    public Pipeline() {
        this.listeDeTweetsApprentissage = FXCollections.observableArrayList();
        this.listeDeTweetsApprentissageNettoye = FXCollections.observableArrayList();
    }

    public ObservableList<Tweet> getListeDeTweetsApprentissageNettoye() {
        return listeDeTweetsApprentissageNettoye;
    }

    public void setListeDeTweetsApprentissageNettoye(ObservableList<Tweet> listeDeTweetsApprentissageNettoye) {
        this.listeDeTweetsApprentissageNettoye = listeDeTweetsApprentissageNettoye;
    }

    public ObservableList<Tweet> getListeDeTweetsApprentissage() {
        return listeDeTweetsApprentissage;
    }

    public void setListeDeTweetsApprentissage(ObservableList<Tweet> listeDeTweetsApprentissage) {
        this.listeDeTweetsApprentissage = listeDeTweetsApprentissage;
    }

    public void ajouterUnTweetdApprentissage(Tweet tweetApprentissage){
        this.listeDeTweetsApprentissage.add( tweetApprentissage );
    }

    public void ajouterUnTweetdApprentissageAlaListeNottoyee(Tweet tweetApprentissage){
        this.listeDeTweetsApprentissageNettoye.add( tweetApprentissage );
    }


    public void supprimerLeTweetDapprentissage(Tweet tweetASupprimer) {
        //supprimer Tweets donc  nhkem un String et je le supprime ok !un tweet Tweet pakois st!ring ok
        for(int i=0; i< listeDeTweetsApprentissage.size(); i++){

            if(listeDeTweetsApprentissage.get(i).getId().equals(tweetASupprimer.getId())){
                //ksk tu veux faire si les ids son les meme ?
                listeDeTweetsApprentissage.remove(i);
                break;
            }
        }
    }
}
