package com.apphelper;

import com.app.model.Tweet;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import java.io.*;

import static jdk.nashorn.internal.objects.Global.println;

/**
 * Created by hiba on 02/05/2016.
 */
public class EnregistreurFichierCsv {

    private final CsvWriter writer;
    private final ByteArrayOutputStream csvResult;

    public EnregistreurFichierCsv() {

        // Writing to an in-memory byte array. This will be printed out to the standard output so you can easily see the result.
        csvResult = new ByteArrayOutputStream();

        // CsvWriter (and all other file writers) work with an instance of java.io.Writer
        Writer outputWriter = new OutputStreamWriter(csvResult);

        //##CODE_START
        CsvWriterSettings settings = new CsvWriterSettings();
        // Sets the character sequence to write for the values that are null.
        settings.setNullValue("?");

        //Changes the comment character to -
        settings.getFormat().setComment('-');

        // Sets the character sequence to write for the values that are empty.
        settings.setEmptyValue("!");

        // writes empty lines as well.
        settings.setSkipEmptyLines(false);

        // Creates a writer with the above settings;
        writer = new CsvWriter(outputWriter, settings);
    }

    public void enregistrerUnTweetEnStreaming(Tweet tweet){

       writer.writeRow( tweet.toTableOfObjects() );

    }

    public void saveAndClose( String nomDuFicher){
        // we must close the writer. This also closes the java.io.Writer you used to create the CsvWriter instance
        // note no checked exceptions are thrown here. If anything bad happens you'll get an IllegalStateException wrapping the original error.
        writer.close();
        //##CODE_END
        // Let's just print the resulting CSV
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File( nomDuFicher ));
            csvResult.writeTo( fos );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeAndPrint(String[] data, CsvWriterSettings settings, String message) {
        CsvWriter writer = new CsvWriter(settings);
        String result = writer.writeRowToString(data);
        println(message + ":\n\t" + result);
    }


}
