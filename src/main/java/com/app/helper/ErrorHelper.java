package com.app.helper;

import javafx.stage.StageStyle;
import org.controlsfx.dialog.ExceptionDialog;


/**
 * Created by Oussama on 09/05/2016.
 */
public class ErrorHelper {

    public static void showErrorDialog(Exception ex) {
        ExceptionDialog dlg = new ExceptionDialog( ex );
        dlg.getDialogPane().setHeaderText( "  Oooops !" );
        dlg.initStyle(StageStyle.DECORATED );
        dlg.showAndWait().ifPresent(result -> System.out.println("Result is " + result));
    }
}
