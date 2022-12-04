package org.network.opensky.flights.apis;

import org.network.opensky.flights.models.Flight;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface FlightService {

    // OpenSky REST API implementation with Retrofit and Basic Authentication.
    // https://openskynetwork.github.io/opensky-api/rest.html

    // Flights in Time Interval.
    // https://openskynetwork.github.io/opensky-api/rest.html#flights-in-time-interval
    @GET("flights/all")
    Call<List<Flight>> getFlightsInTimeInterval(
            @Header("Authorization") String authorization,
            @Query("begin") Integer begin,
            @Query("end") Integer end
    );


    // Flights by Aircraft.
    // https://openskynetwork.github.io/opensky-api/rest.html#flights-by-aircraft
    @GET("flights/aircraft")
    Call<List<Flight>> getFlightsByAircraft(
            @Header("Authorization") String authorization,
            @Query("icao24") String icao24,
            @Query("begin") Integer begin,
            @Query("end") Integer end
    );

    // Arrivals by Airport.
    // https://openskynetwork.github.io/opensky-api/rest.html#arrivals-by-airport
    @GET("flights/arrival")
    Call<List<Flight>> getArrivalsByAirport(
            @Header("Authorization") String authorization,
            @Query("airport") String airport,
            @Query("begin") Integer begin,
            @Query("end") Integer end
    );

    // Departures by Airport.
    // https://openskynetwork.github.io/opensky-api/rest.html#departures-by-airport
    @GET("flights/departure")
    Call<List<Flight>> getDeparturesByAirport(
            @Header("Authorization") String authorization,
            @Query("airport") String airport,
            @Query("begin") Integer begin,
            @Query("end") Integer end
    );
}
