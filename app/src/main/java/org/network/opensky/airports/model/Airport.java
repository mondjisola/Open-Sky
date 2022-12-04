package org.network.opensky.airports.model;

public interface Airport {
    Integer getId();
    String getIcao();
    String getIata();
    String getName();
    String getCity();
    String getState();
    String getCountry();
    String getIso();
    Integer getElevation();
    Double getLatitude();
    Double getLongitude();
    String getTimezone();
}
