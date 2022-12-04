package org.network.opensky.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.elevation.SurfaceColors;

import org.network.opensky.R;
import org.network.opensky.databinding.FragmentMainBinding;

import java.util.Objects;

import dev.chrisbanes.insetter.Insetter;

public class MainFragment extends Fragment {

    public static final String TAG = "MAIN_FRAGMENT";

    protected FragmentMainBinding mFragmentMainBinding;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Get the saved state of the activity if there is one.
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Making the activity full screen for edge-to-edge design.
        WindowCompat.setDecorFitsSystemWindows(requireActivity().getWindow(), false);

        // Setting background color of system navigation bar.
        requireActivity().getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(requireContext()));

        // Inflate the layout for this fragment
        mFragmentMainBinding = FragmentMainBinding.inflate(getLayoutInflater());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        new AppBarConfiguration.Builder(
                R.id.navigation_search,
                R.id.navigation_explore,
                R.id.navigation_saved
        ).build();
        NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.nav_host_fragment_main);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();
        NavigationUI.setupWithNavController(mFragmentMainBinding.bottomNavigation, navController);

        // Handle overlaps behaviors of edge-to-edge design by using the Insetter library.
        Insetter.builder()
                .margin(WindowInsetsCompat.Type.navigationBars())
                .applyToView(mFragmentMainBinding.navHostFragmentMain);

        // Return the inflated layout.
        return mFragmentMainBinding.getRoot();
    }
}