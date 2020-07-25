package com.example.sampleapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp.R;
import com.example.sampleapp.adapter.LaptopRecyclerAdapter;
import com.example.sampleapp.listener.OnLaptopRecyclerItemClickListener;
import com.example.sampleapp.listener.OnLaptopSelectListener;
import com.example.sampleapp.model.Laptop;

import java.util.List;

public class ChooserFragment extends Fragment {

    private static final String TAG = ChooserFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private List<Laptop> laptopList;
    private LaptopRecyclerAdapter adapter;
    private OnLaptopSelectListener laptopSelectListener;

    public ChooserFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chooser, container, false);
        recyclerView = view.findViewById(R.id.laptop_list);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        laptopList = Laptop.getSampleList();

        adapter = new LaptopRecyclerAdapter(laptopList);
        adapter.setItemClickListener(new OnLaptopRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Laptop selected = null;
                try {
                    selected = laptopList.get(position);
                } catch (IndexOutOfBoundsException e) {
                    Log.e(TAG, "Retrieving laptop info failed", e);
                }
                if (selected == null) {
                    return;
                }

                if (laptopSelectListener != null) {
                    laptopSelectListener.onLaptopSelect(selected);
                }
            }
        });

        recyclerView.setAdapter(adapter);
    }

    public void setLaptopSelectListener(OnLaptopSelectListener listener) {
        this.laptopSelectListener = listener;
    }
}
