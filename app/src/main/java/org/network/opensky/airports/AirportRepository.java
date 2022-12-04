package org.network.opensky.airports;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import org.network.opensky.airports.database.AirportDatabase;
import org.network.opensky.airports.database.entities.AirportEntity;

import java.util.List;

/**
 * Repository handling the work with airports.
 */
public class AirportRepository {

    private static AirportRepository sInstance;

    private final AirportDatabase mDatabase;
    //    private MediatorLiveData<List<ProductEntity>> mObservableProducts;
    private MediatorLiveData<List<AirportEntity>> mObservableAirports;

    private AirportRepository(final AirportDatabase database) {
        mDatabase = database;
        mObservableAirports = new MediatorLiveData<>();
        mObservableAirports.addSource(mDatabase.airportDao().loadAllAirports(),
                airportEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableAirports.postValue(airportEntities);
                    }
                });
    }

    public static AirportRepository getInstance(final AirportDatabase database) {
        if (sInstance == null) {
            synchronized (AirportRepository.class) {
                if (sInstance == null) {
                    sInstance = new AirportRepository(database);
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of airports from the database and get notified when the data changes.
     */
    public LiveData<List<AirportEntity>> getAirports() {
        return mObservableAirports;
    }

    public LiveData<AirportEntity> loadAirport(final int airportId) {
        return mDatabase.airportDao().loadAirport(airportId);
    }

    public AirportEntity loadAirportSync(final int airportId) {
        return mDatabase.airportDao().loadAirportSync(airportId);
    }

    public LiveData<AirportEntity> loadAirportByICAO(final String icao) {
        return mDatabase.airportDao().loadAirportByICAO(icao);
    }

    public LiveData<List<AirportEntity>> searchAirports(String query) {
        return mDatabase.airportDao().searchAllAirports(query);
    }

}
