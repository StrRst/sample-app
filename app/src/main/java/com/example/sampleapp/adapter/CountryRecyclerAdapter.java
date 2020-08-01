package com.example.sampleapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp.R;
import com.example.sampleapp.listener.OnCountryRecyclerItemClickListener;
import com.example.sampleapp.model.Country;

import java.util.List;

public class CountryRecyclerAdapter extends RecyclerView.Adapter<CountryRecyclerAdapter.ViewHolder> {

    private List<Country> items;
    private OnCountryRecyclerItemClickListener listener;

    public CountryRecyclerAdapter(List<Country> items) {
        this.items = items;
    }

    public CountryRecyclerAdapter(List<Country> items, OnCountryRecyclerItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country = items.get(position);

        holder.name.setText(country.getName());
        holder.region.setText(country.getRegion());
        holder.flag.setImageResource(R.drawable.ic_flag_placeholder);

        if (position == items.size() - 1) {
            holder.divider.setVisibility(View.INVISIBLE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<Country> getItems() {
        return items;
    }

    public void setItems(List<Country> items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView flag;
        TextView name;
        TextView region;
        View divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            flag = itemView.findViewById(R.id.country_flag);
            name = itemView.findViewById(R.id.country_name);
            region = itemView.findViewById(R.id.country_region);
            divider = itemView.findViewById(R.id.divider);
        }
    }
}
