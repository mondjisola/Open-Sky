package org.network.opensky.airports.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.network.opensky.R;
import org.network.opensky.airports.viewmodels.AirportViewModel;
import org.network.opensky.databinding.AirportFragmentBinding;


public class AirportFragment extends Fragment {

    private static final String KEY_AIRPORT_ID = "airport_id";

    private AirportFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.airport_fragment, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AirportViewModel.Factory factory = new AirportViewModel.Factory(
                requireActivity().getApplication(), requireArguments().getInt(KEY_AIRPORT_ID));

        final AirportViewModel model = new ViewModelProvider(this, factory)
                .get(AirportViewModel.class);

        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        mBinding.setAirportViewModel(model);

        subscribeToModel(model);
    }

    private void subscribeToModel(final AirportViewModel model) {
        // Observe comments
    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        super.onDestroyView();
    }

    /** Creates airport fragment for specific airport ID */
    public static AirportFragment forAirport(int airportId) {
        AirportFragment fragment = new AirportFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_AIRPORT_ID, airportId);
        fragment.setArguments(args);
        return fragment;
    }
}