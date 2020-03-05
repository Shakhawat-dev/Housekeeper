package com.example.housekeeper.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomDate {
    static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    public static String getCurrentDate() {
        String cDate = "";
        try {
            Date c = Calendar.getInstance().getTime();
            System.out.println("Current time => " + c);
            cDate = df.format(c);
        } catch (Exception e) {
            Log.e(Data.TAG, "Date Convert ERROR:" + e.toString());
        }

        return cDate;
    }
}
