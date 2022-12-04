package org.network.opensky.airports.ui;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.network.opensky.R;
import org.network.opensky.airports.model.Airport;
import org.network.opensky.databinding.ItemAirportsListBinding;

import java.util.List;

public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.AirportViewHolder> {

    List<? extends Airport> mAirportList;
    final AirportClickCallback mAirportClickCallback;

    public AirportAdapter(AirportClickCallback clickCallback) {
        mAirportClickCallback = clickCallback;
        setHasStableIds(true);
    }

    public void setAirportList(final List<? extends Airport> airportList) {
        if (mAirportList == null) {
            mAirportList = airportList;
            notifyItemRangeInserted(0, airportList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mAirportList.size();
                }

                @Override
                public int getNewListSize() {
                    return airportList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mAirportList.get(oldItemPosition).getId() ==
                            airportList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Airport newAirport = airportList.get(newItemPosition);
                    Airport oldAirport = mAirportList.get(oldItemPosition);
                    return newAirport.getId() == oldAirport.getId()
                            && TextUtils.equals(newAirport.getIcao(), oldAirport.getIcao());
                }
            });
            mAirportList = airportList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    @NonNull
    public AirportAdapter.AirportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAirportsListBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_airports_list,
                        parent, false);
        binding.setCallback(mAirportClickCallback);
        return new AirportAdapter.AirportViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AirportAdapter.AirportViewHolder holder, int position) {
        holder.binding.setAirport(mAirportList.get(position));
        if (TextUtils.isEmpty(mAirportList.get(position).getCity())) {
            holder.binding.airportCityCountrySeparator.setVisibility(View.GONE);
        } else {
            holder.binding.airportCityCountrySeparator.setVisibility(View.VISIBLE);
        }
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mAirportList == null ? 0 : mAirportList.size();
    }

    @Override
    public long getItemId(int position) {
        return mAirportList.get(position).getId();
    }

    static class AirportViewHolder extends RecyclerView.ViewHolder {

        final ItemAirportsListBinding binding;

        public AirportViewHolder(ItemAirportsListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
