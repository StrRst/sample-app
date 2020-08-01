package com.example.sampleapp.activity;

import android.os.Bundle;

import com.example.sampleapp.R;
import com.example.sampleapp.activity.base.BaseActivity;
import com.example.sampleapp.fragment.ChooserFragment;
import com.example.sampleapp.fragment.ViewerFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ChooserFragment chooserFragment;
    private ViewerFragment viewerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar(getString(R.string.main_activity_title));

        chooserFragment = (ChooserFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_chooser);

        if (inLandscapeMode) {
            initLandscapeOrientation();
        } else {
            initPortraitOrientation();
        }
    }

    private void initLandscapeOrientation() {
        viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_viewer);
    }

    private void initPortraitOrientation() {

    }
}
