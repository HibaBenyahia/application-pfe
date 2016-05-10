package com.app.ui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
    private void chargerLesDonnees(){
        System.out.println("ko");
        String path = ClassLoader.getSystemClassLoader().getResource("images/notification-succes.png").toExternalForm();
        ImageView graphic = new ImageView( path );
        Notifications notificationBuilder = Notifications.create()
                .title("Application PFE H.B")
                .text("Chargement des données a été terminé avec succès !")
                .graphic(graphic)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent arg0) {

                        System.out.println("Aaaay! 3awerti la notification!");
                    }
                });
        notificationBuilder.show();
    }
}
