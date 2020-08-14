package com.example.sampleapp.activity;

import com.example.sampleapp.base.BaseActivity;
import com.example.sampleapp.base.BaseFragment;
import com.example.sampleapp.fragment.MainContainerFragment;
import com.example.sampleapp.presenter.MainContainerPresenter;

public class MainActivity extends BaseActivity {

    @Override
    protected BaseFragment initFragment() {
        return new MainContainerFragment();
    }

    @Override
    protected void setPresenter(BaseFragment fragment) {
        ((MainContainerFragment) fragment).setPresenter(new MainContainerPresenter());
    }
}
