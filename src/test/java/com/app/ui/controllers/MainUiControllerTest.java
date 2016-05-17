package com.app.ui.controllers;

import com.app.helper.ErrorHelper;
import com.sun.deploy.util.Waiter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.TimeoutException;

import static com.app.helper.ErrorHelper.showErrorDialog;

/**
 * Created by Oussama on 13/05/2016.
 */
public class MainUiControllerTest extends ApplicationTest{

    @FXML
    private TitledPane tpPreTraitement;
    @FXML
    private Button btnChargementDonnees;
    @FXML
    private Button btnNettoyageDeTweets;


    @Before
    public void setUp(){
        tpPreTraitement = find("#tpPreTraitement");
        btnChargementDonnees = find("#btnChargementDeDonnees");
        btnNettoyageDeTweets = find("#btnNettoyageDeTweets");
    }

    /* Just a shortcut to retrieve widgets in the GUI. */
    public <T extends Node> T find(final String query) {
        /** TestFX provides many operations to retrieve elements from the loaded GUI. */
        return lookup(query).query();
    }


    /* IMO, it is quite recommended to clear the ongoing events, in case of. */
    @After
    public void tearDown() throws TimeoutException {
        /* Close the window. It will be re-opened at the next test. */
        FxToolkit.hideStage();
        release(new KeyCode[] {});
        release(new MouseButton[] {});
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent racine = FXMLLoader.load(getClass().getResource("/fxml/main_ui.fxml"));
            stage.setTitle("Application PFE - Hiba Benyahia");
            Scene scene = new Scene( racine );
            stage.setScene( scene );
            stage.show();
            stage.toFront();
        } catch (Exception e) {
            showErrorDialog(e);
        }
    }


    @Test
    public void test_ui_principale(){
        clickOn(tpPreTraitement);
        waitFor( 2000 );

        clickOn(btnChargementDonnees);
        waitFor( 2000 );

        clickOn(btnNettoyageDeTweets);
        waitFor( 2000 );
    }

    private void waitFor(int duration) {
        try {
            Waiter.runAndWait(() -> { (new Thread()).sleep( duration );
                return null;
            });
        } catch (Exception e) {
            ErrorHelper.showErrorDialog( e );
        }
    }
}