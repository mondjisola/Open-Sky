package org.network.opensky.main.ui.saved;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import org.network.opensky.R;
import org.network.opensky.databinding.FragmentSavedBinding;
import org.network.opensky.flights.models.Flight;
import org.network.opensky.flights.models.StateVectorsResponse;
import org.network.opensky.flights.models.Track;
import org.network.opensky.flights.viewmodels.FlightViewModel;
import org.network.opensky.flights.viewmodels.StateVectorViewModel;
import org.network.opensky.flights.viewmodels.TrackViewModel;
import org.network.opensky.flights.views.FlightsListFragment;

import java.util.List;

public class SavedFragment extends Fragment {

    private FragmentSavedBinding fragmentSavedBinding;
    private FlightViewModel flightViewModel;
    private int numberOfFlights = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SavedViewModel savedViewModel = new ViewModelProvider(this).get(SavedViewModel.class);
        fragmentSavedBinding = FragmentSavedBinding.inflate(inflater, container, false);

        flightViewModel = new ViewModelProvider(requireActivity()).get(FlightViewModel.class);
        flightViewModel.init();
        flightViewModel.getArrivalsByAirport("EDDF", 1517227200, 1517230800);
        flightViewModel.getFlightsLiveData().observe(getViewLifecycleOwner(), new Observer<List<Flight>>() {
            @Override
            public void onChanged(List<Flight> flights) {
                if (flights != null) {
                    numberOfFlights = flights.size();
                }
            }
        });

        fragmentSavedBinding.verify.setOnClickListener(v -> {
            Snackbar.make(v, String.valueOf(numberOfFlights), Snackbar.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FlightsListFragment(), FlightsListFragment.TAG)
                    .addToBackStack(null)
                    .commit();
            Snackbar.make(v, "Fragment replaced", Snackbar.LENGTH_SHORT).show();
        });

        return fragmentSavedBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentSavedBinding = null;
    }

}