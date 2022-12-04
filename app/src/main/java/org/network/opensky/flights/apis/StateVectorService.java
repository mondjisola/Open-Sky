package org.network.opensky.flights.apis;

import org.network.opensky.flights.models.StateVectorsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface StateVectorService {

    // OpenSky REST API implementation with Retrofit and Basic Authentication.
    // https://openskynetwork.github.io/opensky-api/rest.html


    // All State Vectors.
    // https://openskynetwork.github.io/opensky-api/rest.html#all-state-vectors
    @GET("states/all")
    Call<StateVectorsResponse> getAllStateVectors(
            @Header("Authorization") String authorization
    );

    // Request state vectors for particular airplanes or times using the request parameters: time & icao24.
    @GET("states/all")
    Call<StateVectorsResponse> getAllStateVectors(
            @Header("Authorization") String authorization,
            @Query("time") Integer time,
            @Query("icao24") String icao24
    );

    // It is also possible to query a certain area defined by a bounding box of WGS84 coordinates.
    // For this purpose, add all of the following parameters: lamin, lomin, lamax, lomax.
    @GET("states/all")
    Call<StateVectorsResponse> getAllStateVectors(
            @Header("Authorization") String authorization,
            @Query("time") Integer time,
            @Query("icao24") String icao24,
            @Query("lamin") Float lamin,
            @Query("lomin") Float lomin,
            @Query("lamax") Float lamax,
            @Query("lomax") Float lomax
    );

    @GET("states/all")
    Call<StateVectorsResponse> getAllStateVectors(
            @Header("Authorization") String authorization,
            @Query("lamin") Float lamin,
            @Query("lomin") Float lomin,
            @Query("lamax") Float lamax,
            @Query("lomax") Float lomax
    );
}
