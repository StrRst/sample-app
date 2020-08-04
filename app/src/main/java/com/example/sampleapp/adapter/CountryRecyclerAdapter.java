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
import com.example.sampleapp.model.CountryItem;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.List;

public class CountryRecyclerAdapter extends RecyclerView.Adapter<CountryRecyclerAdapter.ViewHolder> {

    private List<CountryItem> items;
    private OnCountryRecyclerItemClickListener listener;

    public CountryRecyclerAdapter(List<CountryItem> items) {
        this.items = items;
    }

    public CountryRecyclerAdapter(List<CountryItem> items, OnCountryRecyclerItemClickListener listener) {
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
        CountryItem country = items.get(position);

        holder.name.setText(country.getName());
        holder.region.setText(country.getRegion());

        GlideToVectorYou.init()
                .with(holder.flag.getContext())
                .setPlaceHolder(R.drawable.ic_flag_placeholder, R.drawable.ic_flag_placeholder)
                .load(country.getFlagUrl(), holder.flag);

        if (position == items.size() - 1) {
            holder.divider.setVisibility(View.INVISIBLE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }

        holder.container.setOnClickListener(view -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<CountryItem> getItems() {
        return items;
    }

    public void setItems(List<CountryItem> items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View container;
        AppCompatImageView flag;
        TextView name;
        TextView region;
        View divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView;
            flag = itemView.findViewById(R.id.country_flag);
            name = itemView.findViewById(R.id.country_name);
            region = itemView.findViewById(R.id.country_region);
            divider = itemView.findViewById(R.id.divider);
        }
    }
}
