package org.network.opensky.main.ui.search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.android.material.transition.MaterialSharedAxis;

import org.network.opensky.R;
import org.network.opensky.airports.ui.AirportsListFragment;
import org.network.opensky.databinding.FragmentSearchBinding;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

import dev.chrisbanes.insetter.Insetter;

public class SearchFragment extends Fragment {

    public static final String TAG = "SEARCH_FRAGMENT";
    private FragmentSearchBinding mFragmentSearchBinding;
    SearchViewModel mSearchViewModel;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    // Date format for the begin date and end date text fields.
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

    @SuppressLint({"ClickableViewAccessibility", "NewApi", "SetTextI18n"})
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle searchInstanceState) {
        // Get the saved state of the fragment if there is one.
        super.onViewCreated(Objects.requireNonNull(container), searchInstanceState);

        // Inflate the layout for this fragment.
        mFragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false);

        // Set the view model for this fragment.
        mSearchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);

        // Handle overlaps behaviors of edge-to-edge design by using the Insetter library.
        Insetter.builder()
                .margin(WindowInsetsCompat.Type.navigationBars())
                .applyToView(mFragmentSearchBinding.searchContainer);

        // Material shared axis transition between search fragment and airports list fragment.
        // Exit transition.
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, true));
        // Reenter transition.
        setReenterTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, false));

        // Whenever the user navigate to the search fragment, the search fragment will be
        // opened instead of the airports list fragment if the previous fragment is the
        // airports list fragment.
        Navigation.findNavController(Objects.requireNonNull(container)).navigateUp();

        // Get the search options drop down menu from the layout.
        final MaterialAutoCompleteTextView searchOptionsDropdown = (MaterialAutoCompleteTextView) mFragmentSearchBinding.searchOptionsDropdown;

        // Set the search options drop down menu to the search options from the view model.
        mSearchViewModel.getSearchOptions().observe(getViewLifecycleOwner(), searchOptionsDropdown::setSimpleItems);

        // By default, departure airport and arrival airport text fields are hidden.
        // Time interval layout is also hidden.
        mFragmentSearchBinding.departureAirportTextField.setVisibility(View.GONE);
        mFragmentSearchBinding.arrivalAirportTextField.setVisibility(View.GONE);
        mFragmentSearchBinding.timeIntervalLayout.setVisibility(View.GONE);

        // Set the listener for the search options drop down menu item selection.
        searchOptionsDropdown.setOnItemClickListener((parent, view, position, id) -> {
            Adapter adapter = parent.getAdapter();
            String selectedOption = (String) adapter.getItem(position);
            mSearchViewModel.setSearchOption(selectedOption);
        });

        // Respond to search option changes.
        mSearchViewModel.getSearchOption().observe(getViewLifecycleOwner(), searchOption -> {
            // Dynamically show/hide the departure/arrival airport text fields
            // according to the selected search option.
            switch (searchOption) {
                case "Arrivals by Airport":
                    mFragmentSearchBinding.departureAirportTextField.setVisibility(View.GONE);
                    mFragmentSearchBinding.arrivalAirportTextField.setVisibility(View.VISIBLE);
                    mFragmentSearchBinding.timeIntervalLayout.setVisibility(View.VISIBLE);
                    break;
                case "Departures by Airport":
                    mFragmentSearchBinding.arrivalAirportTextField.setVisibility(View.GONE);
                    mFragmentSearchBinding.departureAirportTextField.setVisibility(View.VISIBLE);
                    mFragmentSearchBinding.timeIntervalLayout.setVisibility(View.VISIBLE);
                    break;
                case "Flights by Both Airports":
                    mFragmentSearchBinding.departureAirportTextField.setVisibility(View.VISIBLE);
                    mFragmentSearchBinding.arrivalAirportTextField.setVisibility(View.VISIBLE);
                    mFragmentSearchBinding.timeIntervalLayout.setVisibility(View.VISIBLE);
                    break;
                default:
                    mFragmentSearchBinding.departureAirportTextField.setVisibility(View.GONE);
                    mFragmentSearchBinding.arrivalAirportTextField.setVisibility(View.GONE);
                    mFragmentSearchBinding.timeIntervalLayout.setVisibility(View.GONE);
                    break;
            }
        });

        //Listeners for the begin date and end date text fields.
        mFragmentSearchBinding.beginDateTextField.setOnClickListener(v -> {
            // MaterialDatePicker for selecting the begin date for time interval to retrieve flights for.
            MaterialDatePicker<Long> beginDatePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select begin date")
                    .setSelection(mSearchViewModel.getBegin().getTime())
                    .build();
            if (!beginDatePicker.isVisible()) {
                beginDatePicker.show(getChildFragmentManager(), "BEGIN_DATE_PICKER");
                beginDatePicker.addOnPositiveButtonClickListener(selection -> {
                    Date selectedDate = new Date(selection);
                    mSearchViewModel.setBegin(selection);
                    mFragmentSearchBinding.beginDateTextField.setText(dateFormat.format(selectedDate));
                });
            }
        });
        mFragmentSearchBinding.endDateTextField.setOnClickListener(v -> {
            // MaterialDatePicker for selecting the end date for time interval to retrieve flights for.
            MaterialDatePicker<Long> endDatePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select end date")
                    .setSelection(mSearchViewModel.getEnd().getTime())
                    .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
                    .build();
            if (!endDatePicker.isVisible()) {
                endDatePicker.show(getChildFragmentManager(), "END_DATE_PICKER");
                endDatePicker.addOnPositiveButtonClickListener(selection -> {
                    Date selectedDate = new Date(selection);
                    mSearchViewModel.setEnd(selection);
                    mFragmentSearchBinding.endDateTextField.setText(dateFormat.format(selectedDate));
                });
            }
        });

        // Listeners for the begin time and end time text fields.
        mFragmentSearchBinding.beginTimeTextField.setOnClickListener(v -> {
            // MaterialTimePicker for selecting the begin time to retrieve flights for.
            MaterialTimePicker beginTimePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(mSearchViewModel.getBegin().getHours())
                    .setMinute(mSearchViewModel.getBegin().getMinutes())
                    .setTitleText("Select begin time")
                    .build();
            if (!beginTimePicker.isVisible()) {
                beginTimePicker.show(getChildFragmentManager(), "BEGIN_TIME_PICKER");
                beginTimePicker.addOnPositiveButtonClickListener(v1 -> {
                    LocalTime selectedTime = LocalTime.of(beginTimePicker.getHour(), beginTimePicker.getMinute());
                    mSearchViewModel.setBegin(selectedTime.getHour(), selectedTime.getMinute());
                    mFragmentSearchBinding.beginTimeTextField.setText(timeFormat.format(mSearchViewModel.getBegin()));
                });
            }
        });
        mFragmentSearchBinding.endTimeTextField.setOnClickListener(v -> {
            // MaterialTimePicker for selecting the end time to retrieve flights for.
            MaterialTimePicker endTimePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(mSearchViewModel.getEnd().getHours())
                    .setMinute(mSearchViewModel.getEnd().getMinutes())
                    .setTitleText("Select end time")
                    .build();
            if (!endTimePicker.isVisible()) {
                endTimePicker.show(getChildFragmentManager(), "END_TIME_PICKER");
                endTimePicker.addOnPositiveButtonClickListener(v1 -> {
                    LocalTime selectedTime = LocalTime.of(endTimePicker.getHour(), endTimePicker.getMinute());
                    mSearchViewModel.setEnd(selectedTime.getHour(), selectedTime.getMinute());
                    mFragmentSearchBinding.endTimeTextField.setText(timeFormat.format(mSearchViewModel.getEnd()));
                });
            }
        });

        // Departure and arrival airport text fields click listeners.
        mFragmentSearchBinding.departureAirportTextFieldEditText.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AirportsListFragment(), AirportsListFragment.DEPARTURE_AIRPORT_TAG)
                    .addToBackStack(null)
                    .commit();
            Snackbar.make(v, "Departure airport text field clicked", Snackbar.LENGTH_SHORT).show();
        });
        mFragmentSearchBinding.arrivalAirportTextFieldEditText.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AirportsListFragment(), AirportsListFragment.ARRIVAL_AIRPORT_TAG)
                    .addToBackStack(null)
                    .commit();
            Snackbar.make(v, "Arrival airport text field clicked", Snackbar.LENGTH_SHORT).show();
        });

        // Setting the departure and arrival airport text fields to the selected airports from the AirportsListFragment.
        mSearchViewModel.getDepartureAirport().observe(getViewLifecycleOwner(), airport -> {
            if (airport != null) {
                mFragmentSearchBinding.departureAirportTextFieldEditText.setText(airport.getName() + " (" + airport.getCity() + (airport.getCity().isEmpty() ? "" : ", ") + airport.getCountry() + ")");
            }
        });
        mSearchViewModel.getArrivalAirport().observe(getViewLifecycleOwner(), airport -> {
            if (airport != null) {
                mFragmentSearchBinding.arrivalAirportTextFieldEditText.setText(airport.getName() + " (" + airport.getCity() + (airport.getCity().isEmpty() ? "" : ", ") + airport.getCountry() + ")");
            }
        });

        mFragmentSearchBinding.searchFlightsButton.setOnClickListener(v -> {
            Snackbar.make(v, "Begin time: " + String.valueOf(mSearchViewModel.getBegin().toInstant().getEpochSecond()) + ", End time: " + String.valueOf(mSearchViewModel.getEnd().toInstant().getEpochSecond()), Snackbar.LENGTH_SHORT).show();
            System.out.println("Begin time: " + String.valueOf(mSearchViewModel.getBegin().toInstant().getEpochSecond()) + ", End time: " + String.valueOf(mSearchViewModel.getEnd().toInstant().getEpochSecond()));
        });

        return mFragmentSearchBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFragmentSearchBinding = null;
    }
}