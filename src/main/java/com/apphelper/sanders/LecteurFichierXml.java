package com.apphelper.sanders;

import com.app.helper.DateHelper;
import com.app.model.Location;
import com.app.model.Tweet;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hiba on 24/04/2016.
 */
public class LecteurFichierXml {

    private ArrayList<Tweet> listeDeTweet;

    private SAXBuilder saxBuilder;
    private File xmlFile;
    private Document document;
    private Element racine;
    private int nombreTweetsAnglais; //1 min
    private int nombreTotalTweetARecuperer;

    private int nombreDeTweetPositif;
    private int nombreDeTweetNegatif;
    private int nombreDeTweetNeutre;
    private int nombreDeTweetIrrelevent;

    public LecteurFichierXml() {
        this.nombreTweetsAnglais = 0;
        this.nombreTotalTweetARecuperer = 0;
        this.listeDeTweet = new ArrayList<Tweet>();
        this.saxBuilder = new SAXBuilder();
        this.xmlFile = new File( ClassLoader.getSystemClassLoader().getResource("sanders_dataset_tweets_sentiment.xml").getPath());

        try {

            this.document = (Document) saxBuilder.build(xmlFile);

        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
        this.racine = document.getRootElement();
    }

    /**
     * Cette m�thode va lire tout les �l�ments du fichier xml et les mettre
     * dans la listeDeTweets
     */
    public void lireFichierDeTweet() {

        System.out.println("la racine du fichier xml est : " + racine.getName());   //test de succ�s de la lecture

        Element nodeList = document.getRootElement();
        //listeDeTweet = (List)nodeList.getChildren("tweet");
        List<Element> listElementTweet = nodeList.getChildren("tweet");


        for (Element elementTweet : listElementTweet) { //ici je suis dans l'elemnt <tweet> de mon fichier xml

            //maintant madabik tsegmi les type ta3 hadoma pour faciliter la cr�ation de tweet apr�s
            String idTweet = elementTweet.getAttributeValue("id");
            String user = elementTweet.getChildText("user");
            String tweettext = elementTweet.getChildText("tweettext");
            Element locationElement = elementTweet.getChild("location");


            Location location = null;
            if (locationElement == null) {
                System.out.println("the location does not exist ! ");
            } else {
                String latitude = elementTweet.getChild("location").getChildText("latitude");
                String longitude = elementTweet.getChild("location").getChildText("longitude");
                location = new Location(longitude, latitude); // c la derniere fois elli nakhdem m3ak b team :p
            }

            String strDate = elementTweet.getChildText("date");
            Date date = DateHelper.parseDateFromString(strDate);

            int favoris = Integer.parseInt(  elementTweet.getChildText("favoris") );//int
            String language = elementTweet.getChildText("language");
            if(language.equals("en")){
                nombreTweetsAnglais ++;

            }
            Element placeElemnt = elementTweet.getChild("placename");
            String placename = "";
            if (placeElemnt != null) {
                placename = elementTweet.getChildText("placename");
            }

            int retweets = Integer.parseInt( elementTweet.getChildText("retweets") );
            double sentiment= Double.parseDouble(elementTweet.getChildText("sentiment"));
            nombreTotalTweetARecuperer++;


            Tweet tweet = new Tweet(idTweet, user, tweettext, location, date,favoris, language, placename, retweets, sentiment);
            listeDeTweet.add( tweet );

        }
    }

    public int getNombreTotalTweetARecuperer() {
        return nombreTotalTweetARecuperer;
    }

    public void setNombreTotalTweetARecuperer(int nombreTotalTweetARecuperer) {
        this.nombreTotalTweetARecuperer = nombreTotalTweetARecuperer;
    }

    public int getNombreTweetsAnglais() {

        return nombreTweetsAnglais;
    }

    public void setNombreTweetsAnglais(int nombreTweetsAnglais) {
        this.nombreTweetsAnglais = nombreTweetsAnglais;
    }

    public ArrayList<Tweet> getListeDeTweet() {
        return listeDeTweet;
    }

    public void setListeDeTweet(ArrayList<Tweet> listeDeTweet) {
        this.listeDeTweet = listeDeTweet;
    }

    public void calculerNomNobmbreDeTweetPositif(){
        this.nombreDeTweetPositif = 0;
        for(Tweet tweet : this.listeDeTweet){
            if(tweet.getSentiment() == 1)
                this.nombreDeTweetPositif ++;

        }
    }

    public void calculerNomNobmbreDeTweetNegatif(){
        this.nombreDeTweetNegatif = 0;
        for(Tweet tweet : this.listeDeTweet){
            if(tweet.getSentiment() == -1)
                this.nombreDeTweetNegatif ++;

        }
     }

    public void calculerNomNobmbreDeTweetNeutre(){
        this.nombreDeTweetNeutre = 0;
        for(Tweet tweet : this.listeDeTweet){
            if(tweet.getSentiment() == 0)
                this.nombreDeTweetNeutre ++;

        }
    }

    public void calculerNomNobmbreDeTweetIrrelevent(){
        this.nombreDeTweetIrrelevent = 0;
        for(Tweet tweet : this.listeDeTweet){
            if(tweet.getSentiment() == 9)
                this.nombreDeTweetIrrelevent ++;

        }
    }

    public int getNombreDeTweetPositif() {
        return nombreDeTweetPositif;
    }

    public void setNombreDeTweetPositif(int nombreDeTweetPositif) {
        this.nombreDeTweetPositif = nombreDeTweetPositif;
    }

    public int getNombreDeTweetNegatif() {
        return nombreDeTweetNegatif;
    }

    public void setNombreDeTweetNegatif(int nombreDeTweetNegatif) {
        this.nombreDeTweetNegatif = nombreDeTweetNegatif;
    }

    public int getNombreDeTweetNeutre() {
        return nombreDeTweetNeutre;
    }

    public void setNombreDeTweetNeutre(int nombreDeTweetNeutre) {
        this.nombreDeTweetNeutre = nombreDeTweetNeutre;
    }

    public int getNombreDeTweetIrrelevent() {
        return nombreDeTweetIrrelevent;
    }

    public void setNombreDeTweetIrrelevent(int nombreDeTweetIrrelevent) {
        this.nombreDeTweetIrrelevent = nombreDeTweetIrrelevent;
    }
}
