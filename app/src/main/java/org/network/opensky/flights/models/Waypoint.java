package org.network.opensky.flights.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Waypoint {

    private Integer time;

    private Float latitude;

    private Float longitude;

    private Float baroAltitude;

    private Float trueTrack;

    private Boolean onGround;

    /**
     * No args constructor for use in serialization
     *
     */
    public Waypoint() { }

    /**
     *
     * @param time Time which the given waypoint is associated with in seconds since epoch (Unix time).
     * @param latitude WGS-84 latitude in decimal degrees. Can be null.
     * @param longitude WGS-84 longitude in decimal degrees. Can be null.
     * @param baroAltitude Barometric altitude in meters. Can be null.
     * @param trueTrack True track in decimal degrees clockwise from north (north=0°). Can be null.
     * @param onGround Boolean value which indicates if the position was retrieved from a surface position report.
     *
     */
    public Waypoint(Integer time, Float latitude, Float longitude, Float baroAltitude, Float trueTrack, Boolean onGround) {
        super();
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.baroAltitude = baroAltitude;
        this.trueTrack = trueTrack;
        this.onGround = onGround;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getBaroAltitude() {
        return baroAltitude;
    }

    public void setBaroAltitude(Float baroAltitude) {
        this.baroAltitude = baroAltitude;
    }

    public Float getTrueTrack() {
        return trueTrack;
    }

    public void setTrueTrack(Float trueTrack) {
        this.trueTrack = trueTrack;
    }

    public Boolean getOnGround() {
        return onGround;
    }

    public void setOnGround(Boolean onGround) {
        this.onGround = onGround;
    }
}
