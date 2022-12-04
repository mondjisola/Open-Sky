package org.network.opensky.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import org.network.opensky.databinding.ActivitySplashScreenBinding;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    protected ActivitySplashScreenBinding splashScreenBinding;

    //After completion of 2000 ms, the next activity will get started.
    private static final int SPLASH_SCREEN_TIME_OUT=2000;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Fullscreen
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        super.onCreate(savedInstanceState);

        // Inflate the layout for by using the view binding
        splashScreenBinding = ActivitySplashScreenBinding.inflate(getLayoutInflater());

        // Set inflated layout as the content view
        setContentView(splashScreenBinding.getRoot());

        new Handler().postDelayed(() -> {
            // on below line we are
            // creating a new intent
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);

            // on below line we are
            // starting a new activity.
            startActivity(intent);

            // on the below line we are finishing
            // our current activity.
            finish();
        }, SPLASH_SCREEN_TIME_OUT);
    }
}