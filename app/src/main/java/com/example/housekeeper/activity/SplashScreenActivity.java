package com.example.housekeeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

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

        // For showing loading animation
        imageViewLoading = findViewById(R.id.loading);
        Loading.showLoadingImage(SplashScreenActivity.this, imageViewLoading);

        language = SharedPrefManager.getInstance(getApplicationContext()).getPhoneAndLanguage().getLanguage();

        Log.i(Data.TAG, "" + language);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Loading.hideLoadingImage(SplashScreenActivity.this, imageViewLoading);
                if (SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()) {
                    // Checking if user have hotel ID
                    String mHotelId = SharedPrefManager.getInstance(getApplicationContext()).getHotel().getHotelId();
                    if (mHotelId != "")
                        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                    else
                        startActivity(new Intent(getApplicationContext(), HotelListActivity.class));
                    finish();
                    return;
                } else {
                    startActivity(new Intent(getApplicationContext(), GetNumberActivity.class));
                    finish();
                }

            }
        }, 2000);


    }
}
