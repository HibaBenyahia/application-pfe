package com.app.temp;

import com.app.model.Emoticone;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static com.app.helper.FileHelper.getReader;

/**
 * Created by Oussama on 16/05/2016.
 */
public class LecteurFichierEmoticones {

    private ObservableList<Emoticone> listeDesEmoticones;

    public LecteurFichierEmoticones() {
        this.listeDesEmoticones = FXCollections.observableArrayList();
    }

    void recupererLesEmoticones(){
        CsvParserSettings settings = new CsvParserSettings();
        CsvParser parser = new CsvParser(settings);
        parser.beginParsing(getReader( ClassLoader.getSystemClassLoader().getResource("imoticones/emoticones.csv").getPath() ));

        String[] row;

        while ((row = parser.parseNext()) != null) {

            Emoticone emoticone = new Emoticone(row[0], row[1]);
            listeDesEmoticones.add( emoticone );

        }
        parser.stopParsing();

    }

    public ObservableList<Emoticone> getListeDesEmoticones() {
        return listeDesEmoticones;
    }

    public void setListeDesEmoticones(ObservableList<Emoticone> listeDesEmoticones) {
        this.listeDesEmoticones = listeDesEmoticones;
    }
}
