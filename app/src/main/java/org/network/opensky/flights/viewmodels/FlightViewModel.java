package org.network.opensky.flights.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.network.opensky.flights.models.Flight;
import org.network.opensky.flights.repositories.FlightRepository;

import java.util.List;

public class FlightViewModel extends AndroidViewModel {

    private FlightRepository mFlightRepository;
    private LiveData<List<Flight>> mFlightsLiveData;

    public FlightViewModel(Application application) {
        super(application);
    }

    public void init() {
        mFlightRepository = new FlightRepository();
        mFlightsLiveData = mFlightRepository.getFlightsLiveData();
    }

    public void getFlightsInTimeInterval(Integer begin, Integer end) {
        mFlightRepository.getFlightsInTimeInterval(begin, end);
    }

    public void getFlightsByAircraft(String icao24, Integer begin, Integer end) {
        mFlightRepository.getFlightsByAircraft(icao24, begin, end);
    }

    public void getArrivalsByAirport(String airport, Integer begin, Integer end) {
        mFlightRepository.getArrivalsByAirport(airport, begin, end);
    }

    public void getDeparturesByAirport(String airport, Integer begin, Integer end) {
        mFlightRepository.getDeparturesByAirport(airport, begin, end);
    }

    public LiveData<List<Flight>> getFlightsLiveData() {
        return mFlightsLiveData;
    }
}

