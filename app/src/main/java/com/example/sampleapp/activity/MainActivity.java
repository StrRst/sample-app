package com.example.sampleapp.activity;

import android.os.Bundle;

import com.example.sampleapp.R;
import com.example.sampleapp.activity.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar(getString(R.string.app_name));
    }
}