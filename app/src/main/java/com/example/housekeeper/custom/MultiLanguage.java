package com.example.housekeeper.custom;

import android.app.Activity;
import android.content.res.Configuration;
import android.util.Log;

import com.example.housekeeper.utils.Data;

import java.util.Locale;

public class MultiLanguage {

    public static void setApplicationlanguage(Activity context, String language) {

        Log.i(Data.TAG,"MultiLanguage: "+language);

        String languageToLoad = "fa"; // your language

        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getBaseContext().getResources().updateConfiguration(config,
                context.getBaseContext().getResources().getDisplayMetrics());

    }


}
