package com.example.sampleapp.activity;

import android.os.Bundle;

import androidx.fragment.app.FragmentContainerView;

import com.example.sampleapp.R;
import com.example.sampleapp.base.BaseActivity;
import com.example.sampleapp.fragment.MainContainerFragment;

public class MainActivity extends BaseActivity {

    private FragmentContainerView fragmentContainerView;
    private MainContainerFragment mainContainerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentContainerView = findViewById(R.id.fragment_container_view);

        mainContainerFragment = (MainContainerFragment) getSupportFragmentManager()
                .findFragmentById(fragmentContainerView.getId());

        if (mainContainerFragment == null) {
            mainContainerFragment = new MainContainerFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(fragmentContainerView.getId(), mainContainerFragment)
                    .commit();
        }
    }
}
