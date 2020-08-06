package com.example.sampleapp.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.example.sampleapp.R;
import com.example.sampleapp.activity.base.BaseActivity;
import com.example.sampleapp.fragment.ChooserFragment;
import com.example.sampleapp.fragment.ViewerFragment;
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
        initToolbarMenu();

        chooserFragment = (ChooserFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_chooser);

        if (inLandscapeMode) {
            initLandscapeOrientation();
        } else {
            initPortraitOrientation();
        }

        performReceivedSearchRequest();
    }

    private void initToolbarMenu() {
        Toolbar toolbar = getToolbar();
        toolbar.inflateMenu(R.menu.main_activity_menu);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_show_search_history:
                    startSearchHistoryActivity();
                    return true;
                default:
                    return false;
            }
        });
    }

    private void startSearchHistoryActivity() {
        Intent intent = new Intent(this, SearchHistoryActivity.class);
        startActivity(intent);
    }

    private void initLandscapeOrientation() {
        viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_viewer);

        chooserFragment.setCountrySelectListener(country -> {
            viewerFragment.setData(country);
        });
    }

    private void initPortraitOrientation() {
        chooserFragment.setCountrySelectListener(country -> {
            Intent intent = new Intent(MainActivity.this, CountryDetailsActivity.class);
            intent.putExtra(Constants.COUNTRY_OBJECT, country);
            startActivity(intent);
        });
    }

    private void performReceivedSearchRequest() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.SEARCH_STRING)) {
            String searchString = intent.getStringExtra(Constants.SEARCH_STRING);
            chooserFragment.handleExternalSearchRequest(searchString);
            intent.removeExtra(Constants.SEARCH_STRING);
        }
    }
}
