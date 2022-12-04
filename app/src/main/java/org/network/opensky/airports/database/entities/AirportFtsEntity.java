package org.network.opensky.airports.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;

@Entity(tableName = "airportsFts")
@Fts4(contentEntity = AirportEntity.class)
public class AirportFtsEntity {
    @ColumnInfo(name = "icao")
    private final String icao;
    @ColumnInfo(name = "iata")
    private final String iata;
    @ColumnInfo(name = "name")
    private final String name;
    @ColumnInfo(name = "city")
    private final String city;
    @ColumnInfo(name = "state")
    private final String state;
    @ColumnInfo(name = "country")
    private final String country;
    @ColumnInfo(name = "iso")
    private final String iso;
    @ColumnInfo(name = "elevation")
    private final int elevation;
    @ColumnInfo(name = "latitude")
    private final double latitude;
    @ColumnInfo(name = "longitude")
    private final double longitude;
    @ColumnInfo(name = "timezone")
    private final String timezone;

    public AirportFtsEntity(String icao,String iata, String name, String city, String state, String country, String iso, int elevation, double latitude, double longitude, String timezone) {
        this.icao = icao;
        this.iata = iata;
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
        this.iso = iso;
        this.elevation = elevation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
    }

    public String getIcao() {
        return icao;
    }

    public String getIata() {
        return iata;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getIso() {
        return iso;
    }

    public int getElevation() {
        return elevation;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTimezone() {
        return timezone;
    }
}
