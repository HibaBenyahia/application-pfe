package com.app.temp;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static com.app.helper.FileHelper.getReader;

/**
 * Created by hiba on 18/05/2016.
 */
public class LecteurFichierMotsVides {

    private ObservableList<String> listeMotsVides ;

    public LecteurFichierMotsVides() {
        this.listeMotsVides = FXCollections.observableArrayList();
    }

    public void recupererMotVidesAnglais(){
        CsvParserSettings settings = new CsvParserSettings();
        CsvParser parser = new CsvParser(settings);
        parser.beginParsing(getReader( ClassLoader.getSystemClassLoader().getResource("motsvides/mots_vides_en.csv").getPath() ));

        String[] row;
        while ((row = parser.parseNext()) != null) {
            listeMotsVides.add( row[0]);
        }

        parser.stopParsing();
    }

    public ObservableList<String> getListeMotsVides() {
        return listeMotsVides;
    }

    public void setListeMotsVides(ObservableList<String> listeMotsVides) {
        this.listeMotsVides = listeMotsVides;
    }
}
