package org.network.opensky.main.ui.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.network.opensky.databinding.FragmentExploreBinding;

import java.util.Objects;

import dev.chrisbanes.insetter.Insetter;

public class ExploreFragment extends Fragment implements OnMapReadyCallback {

    protected GoogleMap exploreMap;
    protected ExploreViewModel exploreViewModel;
    private FragmentExploreBinding fragmentExploreBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Create the view model for the fragment.
        new ViewModelProvider(this).get(ExploreViewModel.class);

        // Bind the fragment to the layout.
        fragmentExploreBinding = FragmentExploreBinding.inflate(inflater, container, false);

        // Handle overlaps behaviors of edge-to-edge design by using the Insetter library.
        Insetter.builder()
                .margin(WindowInsetsCompat.Type.statusBars())
                .applyToView(fragmentExploreBinding.exploreFab);

        // Set the root view of the fragment.
        View exploreBindingRoot = fragmentExploreBinding.getRoot();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().
                findFragmentById(fragmentExploreBinding.map.getId());
        Objects.requireNonNull(mapFragment).getMapAsync(this);

        // Return the root view of the fragment.
        return exploreBindingRoot;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the content view of the fragment to be displayed under the system bars.
        // That is what we call edge-to-edge design.
        WindowCompat.setDecorFitsSystemWindows(requireActivity().getWindow(), false);
        exploreViewModel = new ViewModelProvider(this).get(ExploreViewModel.class);
    }

    // Manipulates the map once available.
    // This callback is triggered when the map is ready to be used.
    // This is where we can add markers or lines, add listeners or move the camera.
    // If Google Play services is not installed on the device, the user will be prompted to install
    // it inside the SupportMapFragment. This method will only be triggered once the user has
    // installed Google Play services and returned to the app.
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        // Set the map.
        exploreMap = googleMap;

        // Add a marker in Sydney and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        exploreMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        exploreMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    // Destroy the fragment view binding when the fragment is destroyed.
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentExploreBinding = null;
    }

}

