package org.network.opensky.flights.apis;

import org.network.opensky.flights.models.Track;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TrackService {

    // OpenSky REST API implementation with Retrofit and Basic Authentication.
    // https://openskynetwork.github.io/opensky-api/rest.html

    // Track by Aircraft.
    // https://openskynetwork.github.io/opensky-api/rest.html#track-by-aircraft
    @GET("tracks/all")
    Call<Track> getTrackByAircraft(
            @Header("Authorization") String authorization,
            @Query("icao24") String icao24,
            @Query("time") Integer time
    );
}

