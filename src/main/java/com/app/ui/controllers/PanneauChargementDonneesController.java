package com.app.ui.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * Created by Oussama on 10/05/2016.
 */
public class PanneauChargementDonneesController {

    @FXML
    private GridPane panneauChargementDonnees;
    @FXML
    private Button btnChargerLesDonnees;
    @FXML
    private ToggleButton btnSanders;
    @FXML
    private ToggleButton btnSentiment140;
    @FXML
    private ProgressBar pbChargement;
    @FXML
    private void chargerLesDonnees(){
        System.out.println("ko");
        showNotification("Hey ;)");

    }

    private void showNotification(String messageDeNorification){
        String path = ClassLoader.getSystemClassLoader().getResource("images/notification-succes.png").toExternalForm();
        ImageView graphic = new ImageView( path );
        Notifications notificationBuilder = Notifications.create()
                .title("Application PFE H.B")
                .text( messageDeNorification )
                .graphic(graphic)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(arg0 -> System.out.println("Aaaay! 3awerti la notification!"));
        notificationBuilder.show();
    }
}
