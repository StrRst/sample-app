package com.example.sampleapp.activity;

import android.os.Bundle;

import androidx.fragment.app.FragmentContainerView;

import com.example.sampleapp.R;
import com.example.sampleapp.base.BaseActivity;
import com.example.sampleapp.fragment.CountryDetailsContainerFragment;

public class CountryDetailsActivity extends BaseActivity {

    private FragmentContainerView fragmentContainerView;
    private CountryDetailsContainerFragment detailsContainerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        fragmentContainerView = findViewById(R.id.fragment_container_view);

        detailsContainerFragment = (CountryDetailsContainerFragment) getSupportFragmentManager()
                .findFragmentById(fragmentContainerView.getId());

        if (detailsContainerFragment == null) {
            detailsContainerFragment = new CountryDetailsContainerFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(fragmentContainerView.getId(), detailsContainerFragment)
                    .commit();
        }
    }
}
