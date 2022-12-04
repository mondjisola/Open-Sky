package org.network.opensky.flights.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Track {

    @SerializedName("icao24")
    @Expose
    private String icao24;

    @SerializedName("startTime")
    @Expose
    private Integer startTime;

    @SerializedName("endTime")
    @Expose
    private Integer endTime;

    @SerializedName("callsign")
    @Expose
    private String callsign;

    @SerializedName("path")
    @Expose
    private List<List<String>> path = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Track() {}

    /**
     *
     * @param icao24 Unique ICAO 24-bit address of the transponder in lower case hex string representation.
     * @param startTime Time of the first waypoint in seconds since epoch (Unix time).
     * @param endTime Time of the last waypoint in seconds since epoch (Unix time).
     * @param callsign Callsign (8 characters) that holds for the whole track. Can be null.
     * @param path Waypoints of the trajectory. Each waypoint contains information's like represented by the Waypoint.java class.
     */

    public Track(String icao24, Integer startTime, Integer endTime, String callsign, List<List<String>> path) {
        super();
        this.icao24 = icao24;
        this.startTime = startTime;
        this.endTime = endTime;
        this.callsign = callsign;
        this.path = path;
    }

    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public List<List<String>> getPath() {
        return path;
    }

    public void setPath(List<List<String>> path) {
        this.path = path;
    }

    public List<Waypoint> mWaypointsSerializer() {
        List<Waypoint> mWaypointsList = new ArrayList<>();
        for (List<String> waypoint : path) {
            Waypoint mWaypoint = new Waypoint(
                    Integer.valueOf(waypoint.get(0)),
                    Float.valueOf(waypoint.get(1)),
                    Float.valueOf(waypoint.get(2)),
                    Float.valueOf(waypoint.get(3)),
                    Float.valueOf(waypoint.get(4)),
                    Boolean.valueOf(waypoint.get(5))
            );
            mWaypointsList.add(mWaypoint);
        }
        return mWaypointsList;
    }

}
