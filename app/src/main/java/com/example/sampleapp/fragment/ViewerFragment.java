package com.example.sampleapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.sampleapp.R;
import com.example.sampleapp.base.BaseFragment;
import com.example.sampleapp.model.CountryItem;

public class ViewerFragment extends BaseFragment {

    private static final String ARG_COUNTRY = "COUNTRY";

    private CountryItem country;

    private AppCompatTextView nativeName;
    private AppCompatTextView capital;
    private AppCompatTextView population;
    private AppCompatTextView area;
    private AppCompatTextView languages;

    public ViewerFragment() {

    }

    public static ViewerFragment newInstance(CountryItem country) {
        ViewerFragment fragment = new ViewerFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_COUNTRY, country);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            country = args.getParcelable(ARG_COUNTRY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);

        nativeName = view.findViewById(R.id.country_native_name);
        capital = view.findViewById(R.id.country_capital);
        population = view.findViewById(R.id.country_population);
        area = view.findViewById(R.id.country_area);
        languages = view.findViewById(R.id.country_languages);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setData(country);
    }

    public void setData(CountryItem country) {
        if (country != null) {
            nativeName.setText(country.getNativeName());
            capital.setText(country.getCapital());
            population.setText(country.getPopulationAsString());
            area.setText(country.getAreaAsString());
            languages.setText(country.getLanguagesAsString());
        }
    }
}
