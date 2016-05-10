package com.app.temp;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import static com.app.helper.FileHelper.getReader;

/**
 * Created by Oussama on 08/05/2016.
 */
public class LecteurFichiersTweets {

    private ObservableList<Integer> listeDeTest;
    private int tweetNumber;

    public LecteurFichiersTweets() {
        this.listeDeTest = FXCollections.observableArrayList();
        this.listeDeTest.addListener((ListChangeListener<Integer>) c -> {
            System.out.println( listeDeTest.get( listeDeTest.size()-1 ).toString() );
        });
    }

    void recupererTweetsOldFashion(){
        CsvParserSettings settings = new CsvParserSettings();
        CsvParser parser = new CsvParser(settings);
        parser.beginParsing(getReader( ClassLoader.getSystemClassLoader().getResource("datasets/sanders_learning_dataset.csv").getPath() ));

        String[] row;
        tweetNumber = 1;
        while ((row = parser.parseNext()) != null) {

            //System.out.println( Arrays.toString( row ) );
            //Tweet tweet = new Tweet(row[1], row[4], row[5], null, DateHelper.parseDateFromString( row[2] ), 0, "en", null, 0, Double.parseDouble( row[0]));

            listeDeTest.add( new Integer(tweetNumber));

            tweetNumber++;


        }
        parser.stopParsing();

    }

    void recupererTweetsEnArrierePlan(){

        Task<Void> lecteurEnBackground = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                CsvParserSettings settings = new CsvParserSettings();
                CsvParser parser = new CsvParser(settings);
                parser.beginParsing(getReader( ClassLoader.getSystemClassLoader().getResource("datasets/sanders_learning_dataset.csv").getPath() ));

                String[] row;
                tweetNumber = 1;
                while ((row = parser.parseNext()) != null) {

                    //System.out.println( Arrays.toString( row ) );
                    //Tweet tweet = new Tweet(row[1], row[4], row[5], null, DateHelper.parseDateFromString( row[2] ), 0, "en", null, 0, Double.parseDouble( row[0]));

                    if (isCancelled()) {
                        break;
                    }

                    listeDeTest.add( new Integer(tweetNumber));

                    final double progress = tweetNumber;
                    Platform.runLater(() -> {
                        System.out.println( progress );
                    });

                    tweetNumber++;


                }
                parser.stopParsing();

                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                showMsg( "Done!" );
            }



        };

        //start Task
        new Thread( lecteurEnBackground ).start();
    }

    private void showMsg(String string){
        System.out.println( string );
    }


}
