package org.network.opensky.flights.models;

public class StateVector {

    private String icao24;

    private String callsign;

    private String originCountry;

    private Integer timePosition;

    private Integer lastContact;

    private Float longitude;

    private Float latitude;

    private Float baroAltitude;

    private Boolean onGround;

    private Float velocity;

    private Float trueTrack;

    private Float verticalRate;

    private Integer[] sensors;

    private Float geoAltitude;

    private String squawk;

    private Boolean spi;

    private Integer positionSource;

    /**
     * No args constructor for use in serialization
     *
     */
    public StateVector() {}

    /**
     *
     * @param icao24 ICAO24 address of the transponder in hex string representation.
     * @param callsign The callsign associated to the vehicle (8 chars). Can be null if no callsign has been received.
     * @param originCountry Country name inferred from the ICAO24 address.
     * @param timePosition Unix timestamp (seconds) for the last position update. Can be null if no position report was received by OpenSky within the past 15s.
     * @param lastContact Unix timestamp (seconds) for the last update in general. This field is updated for any new, valid message
     * @param longitude WGS-84 longitude in decimal degrees. Can be null.
     * @param latitude WGS-84 latitude in decimal degrees. Can be null.
     * @param baroAltitude Barometric altitude in meters. Can be null.
     * @param onGround Boolean value which indicates if the position was retrieved from a surface position report.
     * @param velocity Velocity over ground in m/s. Can be null.
     * @param trueTrack True track in decimal degrees clockwise from north (north=0°). Can be null.
     * @param verticalRate Vertical rate in m/s. A positive value indicates that the airplane is climbing, a negative value indicates that it descends. Can be null.
     * @param sensors IDs of the receivers which contributed to this state vector. Is null if no filtering for sensor was used in the request.
     * @param geoAltitude Geometric altitude in meters. Can be null.
     * @param squawk The transponder code aka Squawk. Can be null.
     * @param spi Whether flight status indicates special purpose indicator.
     * @param positionSource Origin of this state’s position: 0 = ADS-B, 1 = ASTERIX, 2 = MLAT, 3 = FLARM
     */
    public StateVector(String icao24, String callsign, String originCountry, Integer timePosition, Integer lastContact, Float longitude, Float latitude, Float baroAltitude, Boolean onGround, Float velocity, Float trueTrack, Float verticalRate, Integer[] sensors, Float geoAltitude, String squawk, Boolean spi, Integer positionSource) {
        this.icao24 = icao24;
        this.callsign = callsign;
        this.originCountry = originCountry;
        this.timePosition = timePosition;
        this.lastContact = lastContact;
        this.longitude = longitude;
        this.latitude = latitude;
        this.baroAltitude = baroAltitude;
        this.onGround = onGround;
        this.velocity = velocity;
        this.trueTrack = trueTrack;
        this.verticalRate = verticalRate;
        this.sensors = sensors;
        this.geoAltitude = geoAltitude;
        this.squawk = squawk;
        this.spi = spi;
        this.positionSource = positionSource;
    }

    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public Integer getTimePosition() {
        return timePosition;
    }

    public void setTimePosition(Integer timePosition) {
        this.timePosition = timePosition;
    }

    public Integer getLastContact() {
        return lastContact;
    }

    public void setLastContact(Integer lastContact) {
        this.lastContact = lastContact;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getBaroAltitude() {
        return baroAltitude;
    }

    public void setBaroAltitude(Float baroAltitude) {
        this.baroAltitude = baroAltitude;
    }

    public Boolean getOnGround() {
        return onGround;
    }

    public void setOnGround(Boolean onGround) {
        this.onGround = onGround;
    }

    public Float getVelocity() {
        return velocity;
    }

    public void setVelocity(Float velocity) {
        this.velocity = velocity;
    }

    public Float getTrueTrack() {
        return trueTrack;
    }

    public void setTrueTrack(Float trueTrack) {
        this.trueTrack = trueTrack;
    }

    public Float getVerticalRate() {
        return verticalRate;
    }

    public void setVerticalRate(Float verticalRate) {
        this.verticalRate = verticalRate;
    }

    public Integer[] getSensors() {
        return sensors;
    }

    public void setSensors(Integer[] sensors) {
        this.sensors = sensors;
    }

    public Float getGeoAltitude() {
        return geoAltitude;
    }

    public void setGeoAltitude(Float geoAltitude) {
        this.geoAltitude = geoAltitude;
    }

    public String getSquawk() {
        return squawk;
    }

    public void setSquawk(String squawk) {
        this.squawk = squawk;
    }

    public Boolean getSpi() {
        return spi;
    }

    public void setSpi(Boolean spi) {
        this.spi = spi;
    }

    public Integer getPositionSource() {
        return positionSource;
    }

    public void setPositionSource(Integer positionSource) {
        this.positionSource = positionSource;
    }
}
