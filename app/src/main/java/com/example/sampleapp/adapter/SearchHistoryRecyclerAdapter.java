package com.example.sampleapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp.R;
import com.example.sampleapp.listener.OnSearchHistoryRecyclerItemClickListener;
import com.example.sampleapp.model.HistoryItems;

public class SearchHistoryRecyclerAdapter extends RecyclerView.Adapter<SearchHistoryRecyclerAdapter.ViewHolder> {

    private HistoryItems items;
    private OnSearchHistoryRecyclerItemClickListener listener;

    public SearchHistoryRecyclerAdapter(HistoryItems items) {
        this.items = items;
    }

    public SearchHistoryRecyclerAdapter(HistoryItems items, OnSearchHistoryRecyclerItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.searchString.setText(items.get(position));

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

    public HistoryItems getItems() {
        return items;
    }

    public void setItems(HistoryItems items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View container;
        TextView searchString;
        View icon;
        View divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView;
            searchString = itemView.findViewById(R.id.history_search_string);
            icon = itemView.findViewById(R.id.history_list_item_icon);
            divider = itemView.findViewById(R.id.divider);
        }
    }
}
