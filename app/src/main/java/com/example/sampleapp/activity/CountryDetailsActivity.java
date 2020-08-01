package com.example.sampleapp.activity;

import android.os.Bundle;

import com.example.sampleapp.R;
import com.example.sampleapp.activity.base.BaseActivity;

public class CountryDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        initToolbarWithBackButton(getString(R.string.country_details_activity_title));
    }
}
