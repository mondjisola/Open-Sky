package org.network.opensky.flights.viewmodels;

import android.app.Application;
import android.text.TextUtils;

import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;

import org.network.opensky.BaseApp;
import org.network.opensky.airports.AirportRepository;
import org.network.opensky.airports.database.entities.AirportEntity;

import java.util.List;

public class FlightsListViewModel extends AndroidViewModel {

    private final SavedStateHandle mSavedStateHandler;
    public AirportRepository mAirportRepository;
    private final LiveData<AirportEntity> mAirport;

    public FlightsListViewModel(Application application, SavedStateHandle mSavedStateHandle) {
        super(application);
        this.mSavedStateHandler = mSavedStateHandle;

        mAirportRepository = ((BaseApp) application).getRepository();

        // Load the airport from the database
        mAirport = mAirportRepository.loadAirportByICAO(mSavedStateHandle.get("icao24"));
    }

    public LiveData<AirportEntity> getAirport(String icao24) {
       return mAirportRepository.loadAirportByICAO(icao24);
    }

    public LiveData<AirportEntity> getAirport() {
        return mAirport;
    }

}
