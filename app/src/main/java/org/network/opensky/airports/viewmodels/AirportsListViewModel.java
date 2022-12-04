package org.network.opensky.airports.viewmodels;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;

import org.network.opensky.BaseApp;
import org.network.opensky.airports.AirportRepository;
import org.network.opensky.airports.database.entities.AirportEntity;

import java.util.List;

public class AirportsListViewModel extends AndroidViewModel {

    private static final String QUERY_KEY = "QUERY";

    private final SavedStateHandle mSavedStateHandler;
    private final AirportRepository mRepository;
    private final LiveData<List<AirportEntity>> mAirports;

    public AirportsListViewModel(@NonNull Application application,
                                 @NonNull SavedStateHandle savedStateHandle) {
        super(application);
        mSavedStateHandler = savedStateHandle;

        mRepository = ((BaseApp) application).getRepository();

        // Use the savedStateHandle.getLiveData() as the input to switchMap,
        // allowing us to recalculate what LiveData to get from the DataRepository
        // based on what query the user has entered
        mAirports = Transformations.switchMap(
                savedStateHandle.getLiveData("QUERY", null),
                (Function<CharSequence, LiveData<List<AirportEntity>>>) query -> {
                    if (TextUtils.isEmpty(query)) {
                        return mRepository.getAirports();
                    }
                    return mRepository.searchAirports("*" + query + "*");
                });
    }

    public void setQuery(CharSequence query) {
        // Save the user's query into the SavedStateHandle.
        // This ensures that we retain the value across process death
        // and is used as the input into the Transformations.switchMap above
        mSavedStateHandler.set(QUERY_KEY, query);
    }

    /**
     * Expose the LiveData Airports query so the UI can observe it.
     */
    public LiveData<List<AirportEntity>> getAirports() {
        return mAirports;
    }
}