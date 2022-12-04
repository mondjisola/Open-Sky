package org.network.opensky.flights.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import org.network.opensky.airports.AirportRepository;
import org.network.opensky.databinding.FragmentFlightsListBinding;
import org.network.opensky.flights.adapters.FlightAdapter;
import org.network.opensky.flights.adapters.FlightClickCallback;
import org.network.opensky.flights.models.Flight;
import org.network.opensky.flights.viewmodels.FlightViewModel;
import org.network.opensky.flights.viewmodels.FlightsListViewModel;

import java.util.List;

public class FlightsListFragment extends Fragment {

    public static final String TAG = "FLIGHTS_LIST_FRAGMENT";
    FlightViewModel mFlightViewModel;
    protected FragmentFlightsListBinding mFragmentFlightsListBinding;
    protected FlightAdapter mFlightAdapter;
    private AirportRepository mAirportRepository;
    private FlightsListViewModel mFlightsListViewModel;

    public static FlightsListFragment newInstance() {
        return new FlightsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment.
        mFragmentFlightsListBinding = FragmentFlightsListBinding.inflate(inflater, container, false);
        mFlightsListViewModel = new ViewModelProvider(this).get(FlightsListViewModel.class);
        // Set the adapter for the RecyclerView.
        mFlightAdapter = new FlightAdapter(mFlightClickCallback, mFlightsListViewModel);
        mFragmentFlightsListBinding.flightsRecyclerView.setAdapter(mFlightAdapter);

        return mFragmentFlightsListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFlightViewModel = new ViewModelProvider(requireActivity()).get(FlightViewModel.class);

        subscribeUi(mFlightViewModel.getFlightsLiveData());
    }

    private void subscribeUi(LiveData<List<Flight>> liveData) {
        // Update the list when the data changes
        liveData.observe(getViewLifecycleOwner(), myFlights -> {
            if (myFlights != null) {
//                mFragmentFlightsListBinding.setIsLoading(false);
                mFlightAdapter.setFlightList(myFlights);
            } else {
                System.out.println("No flights found");
//                mFragmentFlightsListBinding.setIsLoading(true);
            }
            // EspressoIdlingResource.decrement(); // Set app as idle.
        });
    }

    private final FlightClickCallback mFlightClickCallback = flight -> {
        System.out.println(flight.getIcao24());
    };

}
