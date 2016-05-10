package com.app.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hiba on 25/04/2016.
 */
public class DateHelper {


    public static Date parseDateFromString (String strDate){
        Date dateARetourner = null;

        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd hh:mm:ss z yyyy", Locale.US);

        try {

            dateARetourner = formatter.parse( strDate );
            //System.out.println(dateARetourner);
            //System.out.println(formatter.format(dateARetourner));

        } catch (ParseException e) {
            ErrorHelper.showErrorDialog( e );
        }

        return  dateARetourner;
    }
}
