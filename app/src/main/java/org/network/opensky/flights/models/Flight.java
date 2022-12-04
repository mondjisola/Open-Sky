package org.network.opensky.flights.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flight {

    @SerializedName("icao24")
    @Expose
    private String icao24;

    @SerializedName("firstSeen")
    @Expose
    private Integer firstSeen;

    @SerializedName("estDepartureAirport")
    @Expose
    private String estDepartureAirport;

    @SerializedName("lastSeen")
    @Expose
    private Integer lastSeen;

    @SerializedName("estArrivalAirport")
    @Expose
    private String estArrivalAirport;

    @SerializedName("callsign")
    @Expose
    private String callsign;

    @SerializedName("estDepartureAirportHorizDistance")
    @Expose
    private Integer estDepartureAirportHorizDistance;

    @SerializedName("estDepartureAirportVertDistance")
    @Expose
    private Integer estDepartureAirportVertDistance;

    @SerializedName("estArrivalAirportHorizDistance")
    @Expose
    private Integer estArrivalAirportHorizDistance;

    @SerializedName("estArrivalAirportVertDistance")
    @Expose
    private Integer estArrivalAirportVertDistance;

    @SerializedName("departureAirportCandidatesCount")
    @Expose
    private Integer departureAirportCandidatesCount;

    @SerializedName("arrivalAirportCandidatesCount")
    @Expose
    private Integer arrivalAirportCandidatesCount;

    /**
     * No args constructor for use in serialization
     *
     */
    public Flight() {}

    /**
     *
     * @param icao24 Unique icao24 24-bit address of the transponder in hex string representation. All letters are lower case.
     * @param firstSeen Estimated time of departure for the flight as Unix time (seconds since epoch).
     * @param estDepartureAirport ICAO code of the estimated departure airport. Can be null if the airport could not be identified.
     * @param lastSeen Estimated time of arrival for the flight as Unix time (seconds since epoch).
     * @param estArrivalAirport ICAO code of the estimated arrival airport. Can be null if the airport could not be identified.
     * @param callsign Callsign of the vehicle (8 chars). Can be null if no callsign has been received. If the vehicle transmits multiple callsigns during the flight, we take the one seen most frequently.
     * @param estArrivalAirportHorizDistance Horizontal distance of the last received airborne position to the estimated departure airport in meters.
     * @param estArrivalAirportVertDistance Vertical distance of the last received airborne position to the estimated departure airport in meters.
     * @param estDepartureAirportHorizDistance Horizontal distance of the last received airborne position to the estimated arrival airport in meters.
     * @param estDepartureAirportVertDistance Vertical distance of the last received airborne position to the estimated arrival airport in meters.
     * @param departureAirportCandidatesCount Number of other possible departure airports. These are airports in short distance to estDepartureAirport.
     * @param arrivalAirportCandidatesCount Number of other possible departure airports. These are airports in short distance to estArrivalAirport.
     */
    public Flight(String icao24, Integer firstSeen, String estDepartureAirport, Integer lastSeen, String estArrivalAirport, String callsign, Integer estDepartureAirportHorizDistance, Integer estDepartureAirportVertDistance, Integer estArrivalAirportHorizDistance, Integer estArrivalAirportVertDistance, Integer departureAirportCandidatesCount, Integer arrivalAirportCandidatesCount) {
        super();
        this.icao24 = icao24;
        this.firstSeen = firstSeen;
        this.estDepartureAirport = estDepartureAirport;
        this.lastSeen = lastSeen;
        this.estArrivalAirport = estArrivalAirport;
        this.callsign = callsign;
        this.estDepartureAirportHorizDistance = estDepartureAirportHorizDistance;
        this.estDepartureAirportVertDistance = estDepartureAirportVertDistance;
        this.estArrivalAirportHorizDistance = estArrivalAirportHorizDistance;
        this.estArrivalAirportVertDistance = estArrivalAirportVertDistance;
        this.departureAirportCandidatesCount = departureAirportCandidatesCount;
        this.arrivalAirportCandidatesCount = arrivalAirportCandidatesCount;
    }

    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    public Integer getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(Integer firstSeen) {
        this.firstSeen = firstSeen;
    }

    public String getEstDepartureAirport() {
        return estDepartureAirport;
    }

    public void setEstDepartureAirport(String estDepartureAirport) {
        this.estDepartureAirport = estDepartureAirport;
    }

    public Integer getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Integer lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getEstArrivalAirport() {
        return estArrivalAirport;
    }

    public void setEstArrivalAirport(String estArrivalAirport) {
        this.estArrivalAirport = estArrivalAirport;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public Integer getEstDepartureAirportHorizDistance() {
        return estDepartureAirportHorizDistance;
    }

    public void setEstDepartureAirportHorizDistance(Integer estDepartureAirportHorizDistance) {
        this.estDepartureAirportHorizDistance = estDepartureAirportHorizDistance;
    }

    public Integer getEstDepartureAirportVertDistance() {
        return estDepartureAirportVertDistance;
    }

    public void setEstDepartureAirportVertDistance(Integer estDepartureAirportVertDistance) {
        this.estDepartureAirportVertDistance = estDepartureAirportVertDistance;
    }

    public Integer getEstArrivalAirportHorizDistance() {
        return estArrivalAirportHorizDistance;
    }

    public void setEstArrivalAirportHorizDistance(Integer estArrivalAirportHorizDistance) {
        this.estArrivalAirportHorizDistance = estArrivalAirportHorizDistance;
    }

    public Integer getEstArrivalAirportVertDistance() {
        return estArrivalAirportVertDistance;
    }

    public void setEstArrivalAirportVertDistance(Integer estArrivalAirportVertDistance) {
        this.estArrivalAirportVertDistance = estArrivalAirportVertDistance;
    }

    public Integer getDepartureAirportCandidatesCount() {
        return departureAirportCandidatesCount;
    }

    public void setDepartureAirportCandidatesCount(Integer departureAirportCandidatesCount) {
        this.departureAirportCandidatesCount = departureAirportCandidatesCount;
    }

    public Integer getArrivalAirportCandidatesCount() {
        return arrivalAirportCandidatesCount;
    }

    public void setArrivalAirportCandidatesCount(Integer arrivalAirportCandidatesCount) {
        this.arrivalAirportCandidatesCount = arrivalAirportCandidatesCount;
    }

}
