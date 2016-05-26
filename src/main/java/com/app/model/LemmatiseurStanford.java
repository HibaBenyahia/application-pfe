package com.app.model;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Properties;


/**
 * Created by Oussama on 24/05/2016.
 */


public class LemmatiseurStanford {

    private String phrase;
    private ObservableList<String> listeDeLemmas;
    private ObservableList<String> listeDePosTags;

    public LemmatiseurStanford(String phrase) {
        this.phrase = phrase;

        this.listeDeLemmas = FXCollections.observableArrayList();
        this.listeDePosTags = FXCollections.observableArrayList();
    }

    public void appliquerLaLemmatisationEtPosTag(){
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Initialize an Annotation with some text to be annotated. The text is the argument to the constructor.
        //Annotation annotation = new Annotation("Kosgi Santosh sent an email to Stanford University. He didn't get a reply.");
        Annotation annotation = new Annotation( phrase );

        //Here's why the iPhone SE is a good idea and Apple should continue making smaller screens: onforb.es/1Q382OI pic.twitter.com/DhQbaxKpFC
        // run all the selected Annotators on this text
        pipeline.annotate(annotation);

        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        if (sentences != null && ! sentences.isEmpty()) {

            for (CoreMap sentence : sentences) {

                // Iterate over all tokens in a sentence
                for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {

                    listeDeLemmas.add(token.get(CoreAnnotations.LemmaAnnotation.class));
                    listeDePosTags.add( token.get(CoreAnnotations.PartOfSpeechAnnotation.class) );

                }
            }
        }
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public ObservableList<String> getListeDeLemmas() {
        return listeDeLemmas;
    }

    public void setListeDeLemmas(ObservableList<String> listeDeLemmas) {
        this.listeDeLemmas = listeDeLemmas;
    }

    public ObservableList<String> getListeDePosTags() {
        return listeDePosTags;
    }

    public void setListeDePosTags(ObservableList<String> listeDePosTags) {
        this.listeDePosTags = listeDePosTags;
    }

}
