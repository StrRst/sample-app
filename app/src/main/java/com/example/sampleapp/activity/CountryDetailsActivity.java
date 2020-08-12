package com.example.sampleapp.activity;

import com.example.sampleapp.base.BaseActivity;
import com.example.sampleapp.base.BaseFragment;
import com.example.sampleapp.fragment.CountryDetailsContainerFragment;

public class CountryDetailsActivity extends BaseActivity {

    @Override
    protected BaseFragment initFragment() {
        return new CountryDetailsContainerFragment();
    }
}
