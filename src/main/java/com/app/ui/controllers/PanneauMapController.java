package com.app.ui.controllers;

import com.app.model.Location;
import com.app.model.Tweet;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Oussama on 05/06/2016.
 */
public class PanneauMapController implements Initializable, MapComponentInitializedListener {

    @FXML
    private BorderPane borderPane;
    @FXML
    private GoogleMapView googleMapView;

    private ObservableList<Tweet> listeDeTweetAvecLocation;
    private GoogleMap map;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        googleMapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {

        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(36.7537694, 3.0500379))  //alger
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(3);

        map = googleMapView.createMap(mapOptions);


        for (Tweet tweet : listeDeTweetAvecLocation) {
            Location tweetLocation = tweet.getLocation();
            LatLong location = new LatLong( Double.parseDouble(tweetLocation.getLongitude()), Double.parseDouble(tweetLocation.getLatitude()));
            MarkerOptions markerOptionTweet = new MarkerOptions();
            markerOptionTweet.position( location );
            Marker marker = new Marker( markerOptionTweet );
            map.addMarker( marker );

            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
            String sentiment = tweet.getSentimentApresClassification() == -1.0 ? "Négatif" : "Positif";

            infoWindowOptions.content("<h2>"+sentiment+"</h2>"
                    + tweet.getUser()+" : "+tweet.getTweettext()+"<br>");

            InfoWindow infoWindow = new InfoWindow(infoWindowOptions);
            infoWindow.setPosition(location);

        }


    }

    public void setListeDeTweetsAvecGeoLocation( ObservableList<Tweet> list){
        this.listeDeTweetAvecLocation = list;
    }
}
