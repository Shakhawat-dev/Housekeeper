package com.example.housekeeper;

import com.akexorcist.localizationactivity.ui.LocalizationApplication;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class MainApplication extends LocalizationApplication {

    @NotNull
    @Override
    public Locale getDefaultLanguage() {
        return Locale.ENGLISH;
    }
}

