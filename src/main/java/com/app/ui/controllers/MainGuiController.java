package com.app.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * Created by hiba on 09/05/2016.
 */
public class MainGuiController {
    //nous avons un boutton, progress bar
    //et un Text, qu'on va travailler avec
    //yak hakda? oui tres bien
    //il faut définir ici les varaiables
    //associés à ces objet hakda /

    @FXML
   private  Button btnChargerTweet;
    @FXML
    private ProgressBar wechnssemihaBar;
    @FXML
    private Text wechnsemmihText;

    //une petite méthode qui affiche unmessage
    //sur le Text de linterface
    //cette methode ourrais etre appeler par le boutton
    @FXML
    private void afficherTexte(){
        wechnsemmihText.setText(" saha world ! rak mlih  ");
    }
}
