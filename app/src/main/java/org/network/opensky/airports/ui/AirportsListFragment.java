package org.network.opensky.airports.ui;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import org.network.opensky.R;
import org.network.opensky.airports.database.entities.AirportEntity;
import org.network.opensky.airports.model.Airport;
import org.network.opensky.databinding.FragmentAirportsListBinding;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.snackbar.Snackbar;

import org.network.opensky.airports.viewmodels.AirportsListViewModel;
import org.network.opensky.main.ui.search.SearchFragment;
import org.network.opensky.main.ui.search.SearchViewModel;

import java.util.List;
import java.util.Objects;

public class AirportsListFragment extends Fragment {

    public static final String TAG = "AIRPORT_LIST_FRAGMENT";
    public static final String DEPARTURE_AIRPORT_TAG = "DEPARTURE_AIRPORT_TAG";
    public static final String ARRIVAL_AIRPORT_TAG = "ARRIVAL_AIRPORT_TAG";
    SearchViewModel mSearchViewModel;
    protected FragmentAirportsListBinding mFragmentAirportsListBinding;
    protected AirportAdapter mAirportAdapter;

    public static AirportsListFragment newInstance() {
        return new AirportsListFragment();
    }

    @SuppressLint({"RestrictedApi", "NewApi", "ResourceType"})
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment.
        mFragmentAirportsListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_airports_list, container, false);

        // Focus the mFragmentAirportsListBinding.searchFieldEditText and show the keyboard.
        mFragmentAirportsListBinding.searchFieldEditText.requestFocus();
        mFragmentAirportsListBinding.searchFieldEditText.postDelayed(() -> {
            InputMethodManager keyboard = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            keyboard.showSoftInput(mFragmentAirportsListBinding.searchFieldEditText, 0);
        }, 200);

        // Come back to the previous fragment when the mFragmentAirportsListBinding.searchFieldInputLayout start icon is clicked.
        mFragmentAirportsListBinding.searchFieldInputLayout.setStartIconOnClickListener(v -> {
            requireActivity().onBackPressed();
        });

        // Listen to the mFragmentAirportsListBinding.searchFieldEditText text changes.
//        mFragmentAirportsListBinding.searchFieldEditText.addTextChangedListener(new TextWatcherAdapter() {
//            @Override
//            public void onTextChanged(@NonNull CharSequence s, int start, int before, int count) {
//                mAirportsListViewModel.setQuery(s.toString());
//            }
//        });

        // Setup the adapter for the recycler view.
        mAirportAdapter = new AirportAdapter(mAirportClickCallback);
        mFragmentAirportsListBinding.airportsRecyclerView.setAdapter(mAirportAdapter);

        // Customized item decoration for the recycler view.
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(requireContext(), R.drawable.shp_divider));
        mFragmentAirportsListBinding.airportsRecyclerView.addItemDecoration(dividerItemDecoration);

        // Return the root view.
        return mFragmentAirportsListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final AirportsListViewModel viewModel =
                new ViewModelProvider(this).get(AirportsListViewModel.class);
        mSearchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);
        mFragmentAirportsListBinding.searchFieldEditText.setOnEditorActionListener((v, actionId, event) -> {
            viewModel.setQuery(v.getText().toString());
            return true;
        });

        subscribeUi(viewModel.getAirports());
    }

    private void subscribeUi(LiveData<List<AirportEntity>> liveData) {
        // Update the list when the data changes
        liveData.observe(getViewLifecycleOwner(), myAirports -> {
            if (myAirports != null) {
                mFragmentAirportsListBinding.setIsLoading(false);
                mAirportAdapter.setAirportList(myAirports);
            } else {
               mFragmentAirportsListBinding.setIsLoading(true);
            }
            // espresso does not know how to wait for data binding's loop so we execute changes
            // sync.
            mFragmentAirportsListBinding.executePendingBindings();
        });
    }

    @Override
    public void onDestroyView() {
        mFragmentAirportsListBinding = null;
        mAirportAdapter = null;
        super.onDestroyView();
    }

    private final AirportClickCallback mAirportClickCallback = airport -> {
        // When an airport is clicked, update the view model and the UI.
        if (getTag() != null) {
            if (getTag().equals(DEPARTURE_AIRPORT_TAG)) {
                mSearchViewModel.setDepartureAirport(airport);
                requireActivity().onBackPressed();
            } else if (getTag().equals(ARRIVAL_AIRPORT_TAG)) {
                mSearchViewModel.setArrivalAirport(airport);
                requireActivity().onBackPressed();
            }
        }
    };

}