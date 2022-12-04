package org.network.opensky.flights.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.network.opensky.flights.models.Track;
import org.network.opensky.flights.repositories.TrackRepository;

import java.util.List;

public class TrackViewModel extends AndroidViewModel {

    private TrackRepository mTrackRepository;
    private LiveData<Track> mTrackByAircraftLiveData;

    public TrackViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        mTrackRepository = new TrackRepository();
        mTrackByAircraftLiveData = mTrackRepository.getTrackByAircraftLiveData();
    }

    public void getTrackByAircraft(String icao24, Integer time) {
        mTrackRepository.getTrackByAircraft(icao24, time);
    }

    public LiveData<Track> getTrackByAircraftLiveData() {
        return mTrackByAircraftLiveData;
    }
}
