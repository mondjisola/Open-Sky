package org.network.opensky.flights.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateVectorsResponse {

    @SerializedName("time")
    @Expose
    private Integer time;

    @SerializedName("states")
    @Expose
    private List<List<String>> states = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public StateVectorsResponse() {
    }

    /**
     *
     * @param time The time which the state vectors in this response are associated with. All vectors represent the state of a vehicle with the interval [time -1, time].
     * @param states The state vectors.
     * The states property is a two-dimensional array. Each row represents a state vector like represented by the StateVector.java class.
     */
    public StateVectorsResponse(Integer time, List<List<String>> states) {
        super();
        this.time = time;
        this.states = states;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public List<List<String>> getStates() {
        return states;
    }

    public void setStates(List<List<String>> states) {
        this.states = states;
    }

    public List<StateVector> mStatesSerializer() {
        List<StateVector> mStateVectorsList = new ArrayList<>();
        for (List<String> state : states) {
            StateVector mStateVector = new StateVector(
                    state.get(0),
                    state.get(1),
                    state.get(2),
                    Integer.valueOf(state.get(3)),
                    Integer.valueOf(state.get(4)),
                    Float.valueOf(state.get(5)),
                    Float.valueOf(state.get(6)),
                    Float.valueOf(state.get(7)),
                    Boolean.valueOf(state.get(8)),
                    Float.valueOf(state.get(9)),
                    Float.valueOf(state.get(10)),
                    Float.valueOf(state.get(11)),
                    Integer[].class.cast(state.get(12)),
                    Float.valueOf(state.get(13)),
                    state.get(14),
                    Boolean.valueOf(state.get(15)),
                    Integer.valueOf(state.get(16))
            );
            mStateVectorsList.add(mStateVector);
        }
        return mStateVectorsList;
    }

}