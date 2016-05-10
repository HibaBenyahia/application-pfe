package com.app.ui.controllers;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import static com.app.helper.ErrorHelper.showErrorDialog;

/**
 * Created by hiba on 09/05/2016.
 */
public class MainUiController {

    private final String PANNEAU_CHARGEMENT = "panneau_chargement_donnees.fxml";
    private final String PANNEAU_NETTOYAGE = "panneau_nettoyage_donnees.fxml";
    private final String PANNEAU_EXTRACTION_CARACTERISTIQUE = "panneau_extraction_caracteritique.fxml";
    private final String PANNEAU_APPRENTISSAGE = "panneau_apprentissage.fxml";

    @FXML
    private BorderPane panneauPrincipale;
    @FXML
    private Button btnChargementDeDonnees;
    @FXML
    private Button btnNettoyageDeTweets;
    @FXML
    private Button btnExtractionCaractertiques;
    @FXML
    private Button btnApprentissage;

    @FXML
    private void afficherPaneauChargementTweets() {
        chargerUnNouveauPanneau(PANNEAU_CHARGEMENT);
    }

    @FXML
    private void afficherPanneauNettoyageTweets() {
        chargerUnNouveauPanneau(PANNEAU_NETTOYAGE);
    }

    @FXML
    private void afficherPaneauExtractionCaracteristiques() {
        chargerUnNouveauPanneau(PANNEAU_EXTRACTION_CARACTERISTIQUE);
    }

    @FXML
    private void afficherPaneauApprentissage() {
        chargerUnNouveauPanneau(PANNEAU_APPRENTISSAGE);
    }


    private void chargerUnNouveauPanneau(String nomPanneau) {
        try {
            //charger le panneau depuis son fichier fxml
            FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("fxml/" + nomPanneau));
            Parent panneauProchain = fxmlLoader.load();

            //animer la sortie du panneau precedent
            Node panneauPrecedent = panneauPrincipale.getCenter();
            animerLaSortieDuPanneauPrecedent(panneauPrecedent, panneauProchain);

        } catch (Exception e) {
            showErrorDialog(e);
        }
    }

    private void animerLaSortieDuPanneauPrecedent(Node panneauPrecedent, Node panneauProchain) {

        if (panneauPrecedent == null) {                         //si il n'existe pas un panneau precedent
            animerLEntreeDuProchainPanneau(panneauProchain);    //on anime directement l'entree du panneauProchain
        } else {                                                //sinon on anime la sortie du panneau precedent

            TranslateTransition transition = new TranslateTransition();
            transition.setNode(panneauPrecedent);
            transition.setFromY(0);
            transition.setToY(-1500);
            transition.setDuration(Duration.millis(800));
            //transition.setDelay(Duration.millis(150));
            transition.setInterpolator(Interpolator.EASE_OUT);
            transition.setCycleCount(1);
            transition.setAutoReverse(false);
            panneauPrecedent.setCache(true);
            //node.setCacheHint(CacheHint.SPEED);
            transition.play();
            //puis a la fin de l'animation du sortie on anime l'entree du panneauProchain
            transition.setOnFinished(event -> animerLEntreeDuProchainPanneau(panneauProchain));

        }
    }

    public void animerLEntreeDuProchainPanneau(Node panneauProchain) {
        panneauProchain.setOpacity(0.0);
        panneauPrincipale.setCenter(panneauProchain);
        FadeTransition transition = new FadeTransition();
        transition.setNode(panneauProchain);
        transition.setFromValue(0.0);
        transition.setToValue(1.0);
        transition.setDuration(Duration.millis(800));
        //transition.setDelay(Duration.millis(150));
        transition.setInterpolator(Interpolator.EASE_IN);
        transition.setCycleCount(1);
        transition.setAutoReverse(false);
        panneauProchain.setCache(true);
        //node.setCacheHint(CacheHint.SPEED);
        transition.play();
    }

}
