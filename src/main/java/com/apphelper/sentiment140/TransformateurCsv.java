package com.apphelper.sentiment140;

import com.app.helper.DateHelper;
import com.app.helper.Statics;
import com.app.model.Tweet;
import com.apphelper.EnregistreurFichierCsv;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import static com.app.helper.FileHelper.getReader;

/**
 * Created by Oussama on 02/05/2016.
 */
public class TransformateurCsv {

    private String path;
    private EnregistreurFichierCsv enregistreurFichierCsv;

    public TransformateurCsv() {
        this.path = "training.1600000.processed.noemoticon.csv";
        this.enregistreurFichierCsv = new EnregistreurFichierCsv();
    }

    public void lireEtTransformerLesTweets(){

        CsvParserSettings settings = new CsvParserSettings();
        CsvParser parser = new CsvParser(settings);
        parser.beginParsing(getReader("training.1600000.processed.noemoticon.csv"));

        String[] row;
        while ((row = parser.parseNext()) != null) {

            Tweet tweet = new Tweet(row[1], row[4], row[5], null, DateHelper.parseDateFromString( row[2] ), 0, "en", null, 0, Double.parseDouble( row[0]));
            enregistreurFichierCsv.enregistrerUnTweetEnStreaming( tweet );
        }

        parser.stopParsing();
        enregistreurFichierCsv.saveAndClose( Statics.SENTIMENT140_LEARNING_DATASET );
    }

}
