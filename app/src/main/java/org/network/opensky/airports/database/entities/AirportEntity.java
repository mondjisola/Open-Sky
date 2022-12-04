package org.network.opensky.airports.database.entities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.network.opensky.airports.model.Airport;

@Entity(tableName = "airports")
public class AirportEntity implements Airport {
    @ColumnInfo(name = "id")
    @PrimaryKey
    private Integer id;
    @Nullable
    @ColumnInfo(name = "icao")
    private String icao;
    @ColumnInfo(name = "iata")
    private String iata;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "city")
    private String city;
    @ColumnInfo(name = "state")
    private String state;
    @ColumnInfo(name = "country")
    private String country;
    @ColumnInfo(name = "iso")
    private String iso;
    @ColumnInfo(name = "elevation")
    private Integer elevation;
    @ColumnInfo(name = "latitude")
    private Double latitude;
    @ColumnInfo(name = "longitude")
    private Double longitude;
    @ColumnInfo(name = "timezone")
    private String timezone;

    public AirportEntity() {
    }

    @Ignore
    public AirportEntity(int id, @Nullable String icao, String iata, String name, String city, String state, String country, String iso, int elevation, double latitude, double longitude, String timezone) {
        this.id = id;
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

    public AirportEntity(Airport airport) {
        this.id = airport.getId();
        this.icao = airport.getIcao();
        this.iata = airport.getIata();
        this.name = airport.getName();
        this.city = airport.getCity();
        this.state = airport.getState();
        this.country = airport.getCountry();
        this.iso = airport.getIso();
        this.elevation = airport.getElevation();
        this.latitude = airport.getLatitude();
        this.longitude = airport.getLongitude();
        this.timezone = airport.getTimezone();
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    @Override
    public String getIcao() {
        return icao;
    }

    public void setIcao(@Nullable String icao) {
        this.icao = icao;
    }

    @Override
    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    @Override
    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    @Override
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
