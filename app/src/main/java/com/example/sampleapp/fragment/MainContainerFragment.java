package com.example.sampleapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentContainerView;

import com.example.sampleapp.R;
import com.example.sampleapp.activity.CountryDetailsActivity;
import com.example.sampleapp.activity.SearchHistoryActivity;
import com.example.sampleapp.base.BaseFragment;
import com.example.sampleapp.utils.Constants;

public class MainContainerFragment extends BaseFragment {

    private FragmentContainerView chooserFragmentContainer;
    private FragmentContainerView viewerFragmentContainer;

    private ChooserFragment chooserFragment;
    private ViewerFragment viewerFragment;

    public MainContainerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_container, container, false);

        chooserFragmentContainer = view.findViewById(R.id.chooser_fragment_container);
        viewerFragmentContainer = view.findViewById(R.id.viewer_fragment_container);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initToolbar(view, getString(R.string.main_activity_title));
        initToolbarMenu();

        chooserFragment = (ChooserFragment) getChildFragmentManager()
                .findFragmentById(chooserFragmentContainer.getId());

        if (chooserFragment == null) {
            Intent intent = getActivity().getIntent();

            if (intent.hasExtra(Constants.SEARCH_STRING)) {
                String searchString = intent.getStringExtra(Constants.SEARCH_STRING);
                chooserFragment = ChooserFragment.newInstance(searchString);
            } else {
                chooserFragment = new ChooserFragment();
            }

            getChildFragmentManager().beginTransaction()
                    .add(chooserFragmentContainer.getId(), chooserFragment)
                    .commit();
        }

        if (inLandscapeMode) {
            initLandscapeOrientation();
        } else {
            initPortraitOrientation();
        }
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
        Intent intent = new Intent(getActivity(), SearchHistoryActivity.class);
        startActivity(intent);
    }

    private void initLandscapeOrientation() {
        viewerFragment = (ViewerFragment) getChildFragmentManager()
                .findFragmentById(viewerFragmentContainer.getId());

        if (viewerFragment == null) {
            viewerFragment = new ViewerFragment();
            getChildFragmentManager().beginTransaction()
                    .add(viewerFragmentContainer.getId(), viewerFragment)
                    .commit();
        }

        chooserFragment.setCountrySelectListener(country -> {
            viewerFragment.setData(country);
        });
    }

    private void initPortraitOrientation() {
        chooserFragment.setCountrySelectListener(country -> {
            Intent intent = new Intent(getActivity(), CountryDetailsActivity.class);
            intent.putExtra(Constants.COUNTRY_OBJECT, country);
            startActivity(intent);
        });
    }
}
