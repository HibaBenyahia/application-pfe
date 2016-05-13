package com.app.helper;

import com.sun.org.apache.bcel.internal.util.ClassLoader;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * Created by hiba on 13/05/2016.
 */
public class NotificationHelper {
    public static void showNotification(String messageDeNorification){
        String path = ClassLoader.getSystemClassLoader().getResource("images/notification-succes.png").toExternalForm();
        ImageView graphic = new ImageView( path );
        Notifications notificationBuilder = Notifications.create()
                .title("Application PFE H.B")
                .text( messageDeNorification )
                .graphic(graphic)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(arg0 -> System.out.println("Vous avez cliqué sur la notification!"));
        notificationBuilder.show();
    }
}
