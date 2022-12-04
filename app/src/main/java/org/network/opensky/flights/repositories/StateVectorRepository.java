package org.network.opensky.flights.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import org.network.opensky.flights.apis.StateVectorService;
import org.network.opensky.flights.models.StateVectorsResponse;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StateVectorRepository {

    private static final String BASE_URL = "https://opensky-network.org/api/";
    private static final String USERNAME = "JamaTouk";
    private static final String PASSWORD = "Jamanet1@";

    protected StateVectorService mStateVectorService;
    protected MutableLiveData<StateVectorsResponse> mAllStateVectorsLiveData;

    public StateVectorRepository() {

        mAllStateVectorsLiveData = new MutableLiveData<>();

        OkHttpClient client = new OkHttpClient.Builder().build();

        mStateVectorService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StateVectorService.class);

    }

    public void getAllStateVectors() {

        mStateVectorService.getAllStateVectors(Credentials.basic(USERNAME, PASSWORD))
                .enqueue(new Callback<StateVectorsResponse>() {

                    @Override
                    public void onResponse(@NonNull Call<StateVectorsResponse> call, @NonNull Response<StateVectorsResponse> response) {
                        if (response.body() != null) {
                            mAllStateVectorsLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<StateVectorsResponse> call, @NonNull Throwable t) {
                        mAllStateVectorsLiveData.postValue(null);
                    }
                });
    }

    public void getAllStateVectors(Integer time, String icao24) {

        mStateVectorService.getAllStateVectors(Credentials.basic(USERNAME, PASSWORD), time, icao24)
                .enqueue(new Callback<StateVectorsResponse>() {

                    @Override
                    public void onResponse(@NonNull Call<StateVectorsResponse> call, @NonNull Response<StateVectorsResponse> response) {
                        if (response.body() != null) {
                            mAllStateVectorsLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<StateVectorsResponse> call, @NonNull Throwable t) {
                        mAllStateVectorsLiveData.postValue(null);
                    }
                });
    }

    public void getAllStateVectors(Integer time, String icao24, Float lamin, Float lomin, Float lamax, Float lomax) {

        mStateVectorService.getAllStateVectors(Credentials.basic(USERNAME, PASSWORD), time, icao24, lamin, lomin, lamax, lomax)
                .enqueue(new Callback<StateVectorsResponse>() {

                    @Override
                    public void onResponse(@NonNull Call<StateVectorsResponse> call, @NonNull Response<StateVectorsResponse> response) {
                        if (response.body() != null) {
                            mAllStateVectorsLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<StateVectorsResponse> call, @NonNull Throwable t) {
                        mAllStateVectorsLiveData.postValue(null);
                    }
                });
    }

    public void getAllStateVectors(Float lamin, Float lomin, Float lamax, Float lomax) {

        mStateVectorService.getAllStateVectors(Credentials.basic(USERNAME, PASSWORD), lamin, lomin, lamax, lomax)
                .enqueue(new Callback<StateVectorsResponse>() {

                    @Override
                    public void onResponse(@NonNull Call<StateVectorsResponse> call, @NonNull Response<StateVectorsResponse> response) {
                        if (response.body() != null) {
                            mAllStateVectorsLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<StateVectorsResponse> call, @NonNull Throwable t) {
                        mAllStateVectorsLiveData.postValue(null);
                    }
                });
    }

    public MutableLiveData<StateVectorsResponse> getAllStateVectorsLiveData() {
        return mAllStateVectorsLiveData;
    }
}
