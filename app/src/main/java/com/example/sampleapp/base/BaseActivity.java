package com.example.sampleapp.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import com.example.sampleapp.R;

public abstract class BaseActivity extends AppCompatActivity {

    private FragmentContainerView fragmentContainerView;
    private BaseFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        fragmentContainerView = findViewById(R.id.fragment_container_view);

        fragment = (BaseFragment) getSupportFragmentManager()
                .findFragmentById(fragmentContainerView.getId());

        if (fragment == null) {
            fragment = initFragment();
            setPresenter(fragment);
            getSupportFragmentManager().beginTransaction()
                    .add(fragmentContainerView.getId(), fragment)
                    .commit();
        } else {
            setPresenter(fragment);
        }
    }

    protected abstract BaseFragment initFragment();

    protected abstract void setPresenter(BaseFragment fragment);
}
