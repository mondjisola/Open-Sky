package org.network.opensky.flights.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import org.network.opensky.flights.apis.FlightService;
import org.network.opensky.flights.models.Flight;

import java.util.List;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightRepository {

    private static final String BASE_URL = "https://opensky-network.org/api/";
    private static final String USERNAME = "JamaTouk";
    private static final String PASSWORD = "Jamanet1@";

    protected FlightService mFlightService;
    protected MutableLiveData<List<Flight>> mFlightsLiveData;

    public FlightRepository() {

        mFlightsLiveData = new MutableLiveData<>();

        OkHttpClient client = new OkHttpClient.Builder().build();

        mFlightService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()
                .create(FlightService.class);

    }

    public void getFlightsInTimeInterval(Integer begin, Integer end) {

        mFlightService.getFlightsInTimeInterval(Credentials.basic(USERNAME, PASSWORD), begin, end)
                .enqueue(new Callback<List<Flight>>() {

                    @Override
                    public void onResponse(@NonNull Call<List<Flight>> call, @NonNull Response<List<Flight>> response) {
                        if (response.body() != null) {
                            mFlightsLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Flight>> call, @NonNull Throwable t) {
                        mFlightsLiveData.postValue(null);
                    }
                });
    }

    public void getFlightsByAircraft(String icao24, Integer begin, Integer end) {

            mFlightService.getFlightsByAircraft(Credentials.basic(USERNAME, PASSWORD), icao24, begin, end)
                    .enqueue(new Callback<List<Flight>>() {

                        @Override
                        public void onResponse(@NonNull Call<List<Flight>> call, @NonNull Response<List<Flight>> response) {
                            if (response.body() != null) {
                                mFlightsLiveData.postValue(response.body());
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Flight>> call, @NonNull Throwable t) {
                            mFlightsLiveData.postValue(null);
                        }
                    });
    }

    public void getArrivalsByAirport(String airport, Integer begin, Integer end) {

            mFlightService.getArrivalsByAirport(Credentials.basic(USERNAME, PASSWORD), airport, begin, end)
                    .enqueue(new Callback<List<Flight>>() {

                        @Override
                        public void onResponse(@NonNull Call<List<Flight>> call, @NonNull Response<List<Flight>> response) {
                            if (response.body() != null) {
                                mFlightsLiveData.postValue(response.body());
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Flight>> call, @NonNull Throwable t) {
                            mFlightsLiveData.postValue(null);
                        }
                    });
    }

    public void getDeparturesByAirport(String airport, Integer begin, Integer end) {

            mFlightService.getDeparturesByAirport(Credentials.basic(USERNAME, PASSWORD), airport, begin, end)
                    .enqueue(new Callback<List<Flight>>() {

                        @Override
                        public void onResponse(@NonNull Call<List<Flight>> call, @NonNull Response<List<Flight>> response) {
                            if (response.body() != null) {
                                mFlightsLiveData.postValue(response.body());
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Flight>> call, @NonNull Throwable t) {
                            mFlightsLiveData.postValue(null);
                        }
                    });
    }

    public MutableLiveData<List<Flight>> getFlightsLiveData() {
        return mFlightsLiveData;
    }
}
