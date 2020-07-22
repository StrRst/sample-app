package com.example.sampleapp.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sampleapp.R;
import com.example.sampleapp.activity.base.BaseActivity;
import com.example.sampleapp.fragment.FirstTestFragment;
import com.example.sampleapp.fragment.SecondTestFragment;

public class TestFragmentsActivity extends BaseActivity {

    private AppCompatButton showFirstFragmentButton;
    private AppCompatButton showSecondFragmentButton;
    private AppCompatButton hideFirstFragmentButton;
    private AppCompatButton hideSecondFragmentButton;

    private FirstTestFragment firstFragment;
    private SecondTestFragment secondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragments);

        initToolbar(getString(R.string.app_name));

        firstFragment = new FirstTestFragment();
        secondFragment = new SecondTestFragment();

        initViews();

        setListeners();
    }

    private void initViews() {
        showFirstFragmentButton = findViewById(R.id.btn_show_first_fragment);
        showSecondFragmentButton = findViewById(R.id.btn_show_second_fragment);
        hideFirstFragmentButton = findViewById(R.id.btn_hide_first_fragment);
        hideSecondFragmentButton = findViewById(R.id.btn_hide_second_fragment);
    }

    private void setListeners() {
        showFirstFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(firstFragment);
            }
        });

        showSecondFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(secondFragment);
            }
        });

        hideFirstFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFragment(firstFragment);
            }
        });

        hideSecondFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFragment(secondFragment);
            }
        });
    }

    private void showFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        if (!fragment.isAdded()) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.fragment_container, fragment);
            hideOtherFragments(manager, transaction, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            if (!fragment.isVisible()) {
                FragmentTransaction transaction = manager.beginTransaction();
                hideOtherFragments(manager, transaction, fragment);
                transaction.show(fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
    }

    private void hideOtherFragments(FragmentManager manager, FragmentTransaction transaction, Fragment fragment) {
        for (Fragment f : manager.getFragments()) {
            if (!f.equals(fragment)) {
                transaction.hide(f);
            }
        }
    }

    private void hideFragment(Fragment fragment) {
        if (fragment.isVisible()) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}