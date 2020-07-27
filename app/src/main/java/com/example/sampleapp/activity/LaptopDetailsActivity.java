package com.example.sampleapp.activity;

import android.os.Bundle;

import com.example.sampleapp.R;
import com.example.sampleapp.activity.base.BaseActivity;
import com.example.sampleapp.fragment.ViewerFragment;
import com.example.sampleapp.utils.Constants;

public class LaptopDetailsActivity extends BaseActivity {

    private ViewerFragment viewerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop_details);

        initToolbarWithBackButton(getString(R.string.laptop_details_activity_title));

        String receivedInfo = getIntent().getStringExtra(Constants.LAPTOP_INFO);

        viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_viewer);
        viewerFragment.displayLaptopInfo(receivedInfo);
    }
}
