package com.app.helper;

import java.io.InputStream;

/**
 * Created by Oussama on 27/05/2016.
 */
public class DirectoryHelper {

    private static final String DB_FILE_NAME = "nb_resultats_apprentissage.sqlite";
    private static final String MAC = "Mac";
    private static final String WINDOWS = "Windows";

    public static String getDatabaseFilePath(){
        String pathDeFichierBDD = "";

        //get OS name
        String osName = System.getProperty("os.name");
        System.out.println( osName );

        if ( osName.startsWith(MAC) ) {
            pathDeFichierBDD = "jdbc:sqlite:/Users/a/Documents/BDD Resultat Apprentissage/"+DB_FILE_NAME;

        } else if (osName.startsWith( WINDOWS )) {
            pathDeFichierBDD = "jdbc:sqlite:" + getRepertoireWindowsMesDocuments() + "\\BDD Resultat Apprentissage\\"+DB_FILE_NAME;

        }
        return pathDeFichierBDD;
    }

    public static String getRepertoireWindowsMesDocuments(){
        String myDocuments = null;

        try {
            Process p =  Runtime.getRuntime().exec("reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v personal");
            p.waitFor();

            InputStream in = p.getInputStream();
            byte[] b = new byte[in.available()];
            in.read(b);
            in.close();

            myDocuments = new String(b);
            myDocuments = myDocuments.split("\\s\\s+")[4];

        } catch(Throwable t) {
            t.printStackTrace();
        }

        System.out.println(myDocuments);
        return myDocuments;
    }
}
