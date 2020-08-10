package com.example.sampleapp.activity;

import android.os.Bundle;

import com.example.sampleapp.R;
import com.example.sampleapp.base.BaseActivity;
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

        initToolbar(receivedCountry);

        viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_viewer);

        if (receivedCountry != null) {
            viewerFragment.setData(receivedCountry);
        }
    }

    private void initToolbar(CountryItem country) {
        String toolbarTitle = getString(R.string.country_details_activity_title_constant_part);

        if (country != null) {
            toolbarTitle += country.getName();
        }

        initToolbarWithBackButton(toolbarTitle);
    }
}
