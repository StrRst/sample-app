package com.example.sampleapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sampleapp.R;
import com.example.sampleapp.activity.base.BaseActivity;
import com.example.sampleapp.fragment.ChooserFragment;
import com.example.sampleapp.fragment.ViewerFragment;
import com.example.sampleapp.listener.LaptopSelectListener;
import com.example.sampleapp.model.Laptop;
import com.example.sampleapp.model.LaptopsListProvider;
import com.example.sampleapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private List<Laptop> laptopList;

    private ChooserFragment chooserFragment;
    private ViewerFragment viewerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar(getString(R.string.app_name));

        chooserFragment = (ChooserFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_chooser);
        if (inLandscapeMode) {
            viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_viewer);
        }

        chooserFragment.setLaptopSelectListener(new LaptopSelectListener() {
            @Override
            public void onLaptopSelect(int index) {
                Laptop selected = null;

                try {
                    selected = laptopList.get(index);
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    Log.e(TAG, "Retrieving laptop info failed", e);
                }

                if (selected == null) {
                    return;
                }

                if (inLandscapeMode) {
                    viewerFragment.displayLaptopInfo(selected.toString());
                } else {
                    Intent intent = new Intent(MainActivity.this, LaptopDetailsActivity.class);
                    intent.putExtra(Constants.EXTRA_LAPTOP_INFO, selected.toString());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);

        laptopList = LaptopsListProvider.getList();

        if (fragment.getId() == R.id.fragment_chooser) {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(Constants.LAPTOP_LIST_FRAGMENT_PARAMETER,
                    (ArrayList<? extends Parcelable>) laptopList);
            fragment.setArguments(bundle);
        }
    }
}