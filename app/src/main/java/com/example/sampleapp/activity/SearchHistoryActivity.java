package com.example.sampleapp.activity;

import android.os.Bundle;

import androidx.fragment.app.FragmentContainerView;

import com.example.sampleapp.R;
import com.example.sampleapp.base.BaseActivity;
import com.example.sampleapp.fragment.SearchHistoryContainerFragment;

public class SearchHistoryActivity extends BaseActivity {

    private FragmentContainerView fragmentContainerView;
    private SearchHistoryContainerFragment historyContainerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);

        fragmentContainerView = findViewById(R.id.fragment_container_view);

        historyContainerFragment = (SearchHistoryContainerFragment) getSupportFragmentManager()
                .findFragmentById(fragmentContainerView.getId());

        if (historyContainerFragment == null) {
            historyContainerFragment = new SearchHistoryContainerFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(fragmentContainerView.getId(), historyContainerFragment)
                    .commit();
        }
    }
}