package com.example.sampleapp.activity;

import android.os.Bundle;

import com.example.sampleapp.R;
import com.example.sampleapp.activity.base.BaseActivity;
import com.example.sampleapp.fragment.ViewerFragment;
import com.example.sampleapp.model.CountryItem;
import com.example.sampleapp.utils.Constants;

public class CountryDetailsActivity extends BaseActivity {

    private ViewerFragment viewerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        CountryItem receivedCountry = getIntent().getParcelableExtra(Constants.COUNTRY_OBJECT);

        if (receivedCountry != null) {
            initToolbarWithBackButton(getString(R.string.country_details_activity_title_constant_part) + receivedCountry.getName());

            viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_viewer);
            viewerFragment.setData(receivedCountry);
        }
    }
}
