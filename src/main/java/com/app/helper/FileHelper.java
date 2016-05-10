package com.app.helper;

import java.io.*;

/**
 * Created by Oussama on 09/05/2016.
 */
public class FileHelper {

    public static Reader getReader(String relativePath) {
        try {
            InputStream ips = new FileInputStream( relativePath );

            return new InputStreamReader(ips, "UTF-8");
        } catch (Exception e) {
            ErrorHelper.showErrorDialog( e );
            return null;
        }
    }
}
