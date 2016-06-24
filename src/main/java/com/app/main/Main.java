package com.app.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.app.helper.ErrorHelper.showErrorDialog;

/**
 * Created by hiba on 09/05/2016.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {

        Parent racine = FXMLLoader.load(getClass().getResource("/fxml/main_ui.fxml"));
        primaryStage.setTitle("Application PFE - Hiba Benyahia");
        Scene scene = new Scene( racine );
        primaryStage.setScene( scene );
        primaryStage.show();


        } catch (Exception e) {
            showErrorDialog(e);
        }
    }

    public static void main (String [] args){
        launch(args);
    }
}