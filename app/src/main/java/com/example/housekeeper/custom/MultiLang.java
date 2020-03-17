package com.example.housekeeper.custom;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

//import android.support.v7.app.AppCompatActivity;

public class MultiLang {
    public MultiLang(AppCompatActivity mContext, @LocaleManager.LocaleDef String language) {
        LocaleManager.setNewLocale(mContext, language);
        Intent intent = mContext.getIntent();
        mContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
