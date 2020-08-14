package com.example.sampleapp.activity;

import com.example.sampleapp.base.BaseActivity;
import com.example.sampleapp.base.BaseFragment;
import com.example.sampleapp.fragment.SearchHistoryContainerFragment;

public class SearchHistoryActivity extends BaseActivity {

    @Override
    protected BaseFragment initFragment() {
        return new SearchHistoryContainerFragment();
    }

    @Override
    protected void setPresenter(BaseFragment fragment) {

    }
}