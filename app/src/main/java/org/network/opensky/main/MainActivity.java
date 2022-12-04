package org.network.opensky.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.google.android.material.elevation.SurfaceColors;

import org.network.opensky.R;
import org.network.opensky.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    protected ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Making the activity full screen for edge-to-edge design.
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        // Setting background color of system navigation bar.
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));

        // Get the saved state of the activity if there is one.
        super.onCreate(savedInstanceState);

        // Inflating the layout for by using the view binding.
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        // Setting inflated layout as the content view.
        setContentView(mainBinding.getRoot());

        if (savedInstanceState == null) {
            MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, mainFragment, MainFragment.TAG)
                    .commitNow();
        }
    }

}