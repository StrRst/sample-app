package com.example.sampleapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp.R;
import com.example.sampleapp.adapter.CountryRecyclerAdapter;
import com.example.sampleapp.model.Country;

import java.util.ArrayList;
import java.util.List;

public class ChooserFragment extends Fragment {

    private static final String TAG = ChooserFragment.class.getSimpleName();

    private RecyclerView recyclerView;

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
        recyclerView = view.findViewById(R.id.country_list);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Country> list = new ArrayList<>();
        list.add(new Country("Belgium", "Europe"));
        list.add(new Country("China", "Asia"));
        list.add(new Country("Ethiopia", "Africa"));
        list.add(new Country("Norway", "Europe"));

        CountryRecyclerAdapter adapter = new CountryRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
