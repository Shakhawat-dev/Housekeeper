package com.example.housekeeper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.housekeeper.R;
import com.example.housekeeper.sharedPrefManager.SharedPrefManager;
import com.example.housekeeper.utils.Data;
import com.example.housekeeper.utils.Loading;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView imageViewLoading;
    private String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        imageViewLoading = (ImageView) findViewById(R.id.loading);
        Loading.showLoadingImage(SplashScreenActivity.this, imageViewLoading);

        language = SharedPrefManager.getInstance(getApplicationContext()).getPhoneAndLanguage().getLanguage();

        Log.i(Data.TAG, "" + language);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Loading.hideLoadingImage(SplashScreenActivity.this, imageViewLoading);
                if (SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                    return;
                } else {

                    finish();
                    startActivity(new Intent(getApplicationContext(), GetNumberActivity.class));

                }
                finish();
            }
        }, 2000);


    }
}
