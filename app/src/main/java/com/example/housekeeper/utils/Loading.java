package com.example.housekeeper.utils;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.housekeeper.R;

public class Loading {

    public static void showLoadingImage(Activity activity, ImageView imageView) {
        imageView.setVisibility(View.VISIBLE);
        Animation loadAnimation = AnimationUtils.loadAnimation(activity, R.anim.rotation);
        loadAnimation.setInterpolator(new LinearInterpolator());
        imageView.setAnimation(loadAnimation);
    }

    /* access modifiers changed from: private */
    public static void hideLoadingImage(Activity activity, ImageView imageView) {
        imageView.setAnimation(null);
        imageView.setVisibility(View.GONE);
    }
}
