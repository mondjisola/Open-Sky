package org.network.opensky.airports.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.network.opensky.BaseApp;
import org.network.opensky.airports.AirportRepository;
import org.network.opensky.airports.database.entities.AirportEntity;

public class AirportViewModel extends AndroidViewModel {

    private final LiveData<AirportEntity> mObservableAirport;

    private final int mAirportId;

    public AirportViewModel(@NonNull Application application,
                            AirportRepository repository,
                            final int airportId) {
        super(application);
        mAirportId = airportId;
        mObservableAirport = repository.loadAirport(mAirportId);
    }

    /**
     * Expose the LiveData Airport query so the UI can observe it.
     */
    public LiveData<AirportEntity> getAirport() {
        return mObservableAirport;
    }

    /**
     * A creator is used to inject the airport ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the airport ID can be passed in a public method.
     */
    public static class Factory implements ViewModelProvider.Factory {

        @NonNull
        private final Application mApplication;

        private final int mAirportId;

        private final AirportRepository mRepository;

        public Factory(@NonNull Application application, int airportId) {
            mApplication = application;
            mAirportId = airportId;
            mRepository = ((BaseApp) application).getRepository();
        }

        @SuppressWarnings("unchecked")
        @Override
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new AirportViewModel(mApplication, mRepository, mAirportId);
        }
    }
}
