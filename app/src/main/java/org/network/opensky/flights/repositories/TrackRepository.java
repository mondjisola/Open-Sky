package org.network.opensky.flights.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import org.network.opensky.flights.BasicAuthInterceptor;
import org.network.opensky.flights.apis.TrackService;
import org.network.opensky.flights.models.Track;

import java.util.List;

public class TrackRepository {

    private static final String BASE_URL = "https://opensky-network.org/api/";
    private static final String USERNAME = "JamaTouk";
    private static final String PASSWORD = "Jamanet1@";

    protected TrackService mTrackService;
    protected MutableLiveData<Track> mTrackByAircraftLiveData;

    public TrackRepository() {

        mTrackByAircraftLiveData = new MutableLiveData<>();

        OkHttpClient httpClient = new OkHttpClient.Builder().build();

        mTrackService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TrackService.class);

    }

    public void getTrackByAircraft(String icao24, Integer time) {

        mTrackService.getTrackByAircraft(Credentials.basic(USERNAME, PASSWORD), icao24, time)
                .enqueue(new Callback<Track>() {

                    @Override
                    public void onResponse(@NonNull Call<Track> call, @NonNull Response<Track> response) {
                        if (response.body() != null) {
                            mTrackByAircraftLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Track> call, @NonNull Throwable t) {
                        mTrackByAircraftLiveData.postValue(null);
                    }
                });
    }

    public LiveData<Track> getTrackByAircraftLiveData() {
        return mTrackByAircraftLiveData;
    }
}
