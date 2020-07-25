package com.example.sampleapp.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.sampleapp.R;
import com.example.sampleapp.activity.base.BaseActivity;
import com.example.sampleapp.fragment.ChooserFragment;
import com.example.sampleapp.fragment.ViewerFragment;
import com.example.sampleapp.listener.OnLaptopSelectListener;
import com.example.sampleapp.model.Laptop;
import com.example.sampleapp.utils.Constants;

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

        chooserFragment.setLaptopSelectListener(new OnLaptopSelectListener() {
            @Override
            public void onLaptopSelect(Laptop laptop) {
                viewerFragment.displayLaptopInfo(laptop.toString());
            }
        });
    }

    private void initPortraitOrientation() {
        chooserFragment.setLaptopSelectListener(new OnLaptopSelectListener() {
            @Override
            public void onLaptopSelect(Laptop laptop) {
                Intent intent = new Intent(MainActivity.this, LaptopDetailsActivity.class);
                intent.putExtra(Constants.EXTRA_LAPTOP_INFO, laptop.toString());
                startActivity(intent);
            }
        });
    }
}
