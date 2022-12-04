package org.network.opensky.flights.adapters;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.network.opensky.R;
import org.network.opensky.airports.database.entities.AirportEntity;
import org.network.opensky.databinding.ItemFlightsListBinding;
import org.network.opensky.flights.models.Flight;
import org.network.opensky.flights.viewmodels.FlightsListViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {

    List<? extends Flight> mFlightList;
    final FlightClickCallback mFlightClickCallback;
    private final FlightsListViewModel mFlightsListViewModel;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM. yyyy");
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

    public FlightAdapter(FlightClickCallback clickCallback, FlightsListViewModel mFlightsListViewModel) {
        mFlightClickCallback = clickCallback;
        this.mFlightsListViewModel = mFlightsListViewModel;
        setHasStableIds(true);
    }

    public void setFlightList(final List<? extends Flight> flightList) {
        if (mFlightList == null) {
            mFlightList = flightList;
            notifyItemRangeInserted(0, flightList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mFlightList.size();
                }

                @Override
                public int getNewListSize() {
                    return flightList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mFlightList.get(oldItemPosition).getIcao24() ==
                            flightList.get(newItemPosition).getIcao24();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Flight newFlight = flightList.get(newItemPosition);
                    Flight oldFlight = mFlightList.get(oldItemPosition);
                    return newFlight.getIcao24() == oldFlight.getIcao24()
                            && TextUtils.equals(newFlight.getIcao24(), oldFlight.getIcao24());
                }
            });
            mFlightList = flightList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    @NonNull
    public FlightAdapter.FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFlightsListBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_flights_list,
                        parent, false);
        binding.setCallback(mFlightClickCallback);
        return new FlightViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FlightAdapter.FlightViewHolder holder, int position) {
        AirportEntity departure = mFlightsListViewModel.getAirport(mFlightList.get(position).getEstDepartureAirport()).getValue();
        AirportEntity arrival = mFlightsListViewModel.getAirport(mFlightList.get(position).getEstArrivalAirport()).getValue();
        Date departureDate = new Date(mFlightList.get(position).getFirstSeen().longValue() * 1000);
        Date arrivalDate = new Date(mFlightList.get(position).getLastSeen().longValue() * 1000);
        holder.binding.flightIcao24.setText("Flight " + mFlightList.get(position).getIcao24());
        holder.binding.setFlight(mFlightList.get(position));
        holder.binding.flightDepartureDate.setText(simpleDateFormat.format(departureDate));
        holder.binding.flightDepartureTime.setText(timeFormat.format(departureDate));
        holder.binding.flightArrivalDate.setText(simpleDateFormat.format(arrivalDate));
        holder.binding.flightArrivalTime.setText(timeFormat.format(arrivalDate));
        holder.binding.flightDuration.setText(timeDifference(departureDate, arrivalDate));
        holder.binding.setDepartureAirport(departure);
        holder.binding.setArrivalAirport(arrival);
//        holder.binding.flightDepartureAirportFlag.setImageResource(holder.itemView.getContext().getResources().getIdentifier("drawable/" + Objects.requireNonNull(departure.getIcao()).toLowerCase(), null, holder.itemView.getContext().getPackageName()));
//        holder.binding.flightArrivalAirportFlag.setImageResource(holder.itemView.getContext().getResources().getIdentifier("drawable/" + Objects.requireNonNull(arrival.getIcao()).toLowerCase(), null, holder.itemView.getContext().getPackageName()));

        if (TextUtils.isEmpty(holder.binding.flightDepartureAirportCity.getText())) {
            holder.binding.depAcs.setVisibility(View.GONE);
        } else {
            holder.binding.depAcs.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(holder.binding.flightArrivalAirportCity.getText())) {
            holder.binding.arrAcs.setVisibility(View.GONE);
        } else {
            holder.binding.arrAcs.setVisibility(View.VISIBLE);
        }
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mFlightList == null ? 0 : mFlightList.size();
    }

//    @Override
//    public long getItemId(int position) {
//        return mFlightList.get(position).getIcao24();
//    }

    // Method to calculate the time difference between two dates
    // After the operation, we will return a string with the time difference like this: 1d 2h 3m 4s
    public static String timeDifference(Date date1, Date date2) {
        // Calculating the difference in milliseconds
        long differenceInMilliSeconds = Math.abs(date2.getTime() - date1.getTime());

        // Calculating the difference in Years, Months, Weeks, Days, Hours, Minutes and Seconds
        long differenceInYears = (differenceInMilliSeconds / (1000L * 60 * 60 * 24 * 365));
        long differenceInMonths = (differenceInMilliSeconds / (1000L * 60 * 60 * 24 * 30)) % 12;
        long differenceInWeeks = (differenceInMilliSeconds / (1000L * 60 * 60 * 24 * 7)) % 4;
        long differenceInDays = (differenceInMilliSeconds / (1000L * 60 * 60 * 24)) % 7;
        long differenceInHours = (differenceInMilliSeconds / (1000L * 60 * 60)) % 24;
        long differenceInMinutes = (differenceInMilliSeconds / (1000L * 60)) % 60;
        long differenceInSeconds = (differenceInMilliSeconds / 1000) % 60;

        // Returning the time difference in string format
        return (differenceInYears > 0 ? differenceInYears + "y " : "") +
                (differenceInMonths > 0 ? differenceInMonths + "m " : "") +
                (differenceInWeeks > 0 ? differenceInWeeks + "w " : "") +
                (differenceInDays > 0 ? differenceInDays + "d " : "") +
                (differenceInHours > 0 ? differenceInHours + "h " : "") +
                (differenceInMinutes > 0 ? differenceInMinutes + "m " : "") +
                (differenceInSeconds > 0 ? differenceInSeconds + "s " : "");
    }

    static class FlightViewHolder extends RecyclerView.ViewHolder {

        final ItemFlightsListBinding binding;

        public FlightViewHolder(ItemFlightsListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
