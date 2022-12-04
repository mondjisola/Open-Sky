package org.network.opensky.flights.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.network.opensky.flights.models.StateVectorsResponse;
import org.network.opensky.flights.repositories.StateVectorRepository;

public class StateVectorViewModel extends AndroidViewModel {

    private StateVectorRepository mStateVectorRepository;
    private LiveData<StateVectorsResponse> mAllStateVectorsLiveData;

    public StateVectorViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        mStateVectorRepository = new StateVectorRepository();
        mAllStateVectorsLiveData = mStateVectorRepository.getAllStateVectorsLiveData();
    }

    public void getAllStateVectors() {
        mStateVectorRepository.getAllStateVectors();
    }

    public void getAllStateVectors(Integer time, String icao24) {
        mStateVectorRepository.getAllStateVectors(time, icao24);
    }

    public void getAllStateVectors(Integer time, String icao24, Float lamin, Float lomin, Float lamax, Float lomax) {
        mStateVectorRepository.getAllStateVectors(time, icao24, lamin, lomin, lamax, lomax);
    }

    public void getAllStateVectors(Float lamin, Float lomin, Float lamax, Float lomax) {
        mStateVectorRepository.getAllStateVectors(lamin, lomin, lamax, lomax);
    }

    public LiveData<StateVectorsResponse> getAllStateVectorsLiveData() {
        return mAllStateVectorsLiveData;
    }
}
