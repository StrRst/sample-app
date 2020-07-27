package com.example.sampleapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.sampleapp.R;
import com.example.sampleapp.activity.base.BaseActivity;
import com.example.sampleapp.fragment.ChooserFragment;
import com.example.sampleapp.fragment.ViewerFragment;
import com.example.sampleapp.listener.OnLaptopSelectListener;
import com.example.sampleapp.model.Laptop;
import com.example.sampleapp.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ChooserFragment chooserFragment;
    private ViewerFragment viewerFragment;
    private FloatingActionButton addButton;

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

        initAddButton();
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
                intent.putExtra(Constants.LAPTOP_INFO, laptop.toString());
                startActivity(intent);
            }
        });
    }

    private void initAddButton() {
        addButton = findViewById(R.id.add_laptop_btn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddLaptopActivity.class);
                startActivityForResult(intent, Constants.ADD_LAPTOP_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.ADD_LAPTOP_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }
                Laptop laptop = data.getParcelableExtra(Constants.LAPTOP_OBJECT);
                if (laptop == null) {
                    return;
                }
                chooserFragment.addLaptopToList(laptop);
                showShortToast("Laptop added successfully!");
            } else if (resultCode == RESULT_CANCELED) {
                showShortToast("Operation was cancelled!");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
