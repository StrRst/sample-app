package com.example.sampleapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp.R;
import com.example.sampleapp.listener.OnLaptopRecyclerItemClickListener;
import com.example.sampleapp.model.Laptop;

import java.util.List;

public class LaptopRecyclerAdapter extends RecyclerView.Adapter<LaptopRecyclerAdapter.ViewHolder> {

    private List<Laptop> laptopList;
    private OnLaptopRecyclerItemClickListener itemClickListener;

    public LaptopRecyclerAdapter(List<Laptop> laptopList) {
        this.laptopList = laptopList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(view, viewHolder.getAdapterPosition());
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Laptop laptop = laptopList.get(position);

        holder.laptopName.setText(laptop.getName());
        holder.laptopPrice.setText(laptop.getPriceAsText());

        if (position == laptopList.size() - 1) {
            holder.divider.setVisibility(View.INVISIBLE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return laptopList.size();
    }

    public void setItemClickListener(OnLaptopRecyclerItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView laptopName;
        AppCompatTextView laptopPrice;
        View divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            laptopName = itemView.findViewById(R.id.laptop_name);
            laptopPrice = itemView.findViewById(R.id.laptop_price);
            divider = itemView.findViewById(R.id.divider);
        }
    }
}
