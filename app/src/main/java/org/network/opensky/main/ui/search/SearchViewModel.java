package org.network.opensky.main.ui.search;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.network.opensky.airports.model.Airport;

import java.util.Date;

public class SearchViewModel extends ViewModel {

    private final MutableLiveData<String[]> searchOptions  = new MutableLiveData<>();

    private final MutableLiveData<String> searchOption = new MutableLiveData<>();

    private final MutableLiveData<Airport> departureAirport = new MutableLiveData<>();

    private final MutableLiveData<Airport> arrivalAirport = new MutableLiveData<>();

    // Start of time interval to retrieve flights for.
    private final MutableLiveData<Date> begin = new MutableLiveData<>();

    // End of time interval to retrieve flights for.
    private final MutableLiveData<Date> end = new MutableLiveData<>();

    public SearchViewModel() {
        searchOptions.setValue(new String[] {
                "Arrivals by Airport",
                "Departures by Airport",
                "Flights by Both Airports",
        });
        begin.setValue(new Date());
        end.setValue(new Date());
    }

    public LiveData<String[]> getSearchOptions() {
        return searchOptions;
    }

    public void setSearchOption(String selectedOption) {
        switch (selectedOption) {
            case "Arrivals by Airport":
                searchOption.setValue("Arrivals by Airport");
                break;
            case "Departures by Airport":
                searchOption.setValue("Departures by Airport");
                break;
            case "Flights by Both Airports":
                searchOption.setValue("Flights by Both Airports");
                break;
        }
    }

    public LiveData<String> getSearchOption() {
        return searchOption;
    }

    public void setDepartureAirport(Airport airport) {
        departureAirport.setValue(airport);
    }

    public LiveData<Airport> getDepartureAirport() {
        return departureAirport;
    }

    public void setArrivalAirport(Airport airport) {
        arrivalAirport.setValue(airport);
    }

    public LiveData<Airport> getArrivalAirport() {
        return arrivalAirport;
    }

    public void setBegin(Long date) {
        Date tmp = new Date(date);
        begin.setValue(setDate(tmp.getYear(), tmp.getMonth(), tmp.getDate(), getBegin().getHours(), getBegin().getMinutes()));
    }

    public void setBegin(int hours, int minutes) {
        begin.setValue(setDate(getBegin().getYear(), getBegin().getMonth(), getBegin().getDate(), hours, minutes));
    }

    public Date getBegin() {
        return begin.getValue();
    }

    public void setEnd(Long date) {
        Date tmp = new Date(date);
        end.setValue(setDate(tmp.getYear(), tmp.getMonth(), tmp.getDate(), getEnd().getHours(), getEnd().getMinutes()));
    }

    public void setEnd(int hours, int minutes) {
        end.setValue(setDate(getEnd().getYear(), getEnd().getMonth(), getEnd().getDate(), hours, minutes));
    }

    public Date getEnd() {
        return end.getValue();
    }

    public Date setDate(int year, int month, int date, int hours, int minutes) {
        Date tmp = new Date();
        tmp.setYear(year);
        tmp.setMonth(month);
        tmp.setDate(date);
        tmp.setHours(hours);
        tmp.setMinutes(minutes);
        tmp.setSeconds(0);
        return tmp;
    }

}