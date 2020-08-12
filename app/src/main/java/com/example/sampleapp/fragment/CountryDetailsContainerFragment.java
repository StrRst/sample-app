package com.example.sampleapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentContainerView;

import com.example.sampleapp.R;
import com.example.sampleapp.base.BaseFragment;
import com.example.sampleapp.model.CountryItem;
import com.example.sampleapp.utils.Constants;

public class CountryDetailsContainerFragment extends BaseFragment {

    private FragmentContainerView fragmentContainer;
    private ViewerFragment viewerFragment;

    private CountryItem receivedCountry;

    public CountryDetailsContainerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (inLandscapeMode) {
            getActivity().finish();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_details_container, container, false);

        fragmentContainer = view.findViewById(R.id.viewer_fragment_container);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        receivedCountry = getActivity().getIntent().getParcelableExtra(Constants.COUNTRY_OBJECT);

        initToolbar(receivedCountry, view);

        viewerFragment = (ViewerFragment) getChildFragmentManager()
                .findFragmentById(fragmentContainer.getId());

        if (viewerFragment == null) {
            viewerFragment = ViewerFragment.newInstance(receivedCountry);
            getChildFragmentManager().beginTransaction()
                    .add(fragmentContainer.getId(), viewerFragment)
                    .commit();
        }
    }

    private void initToolbar(CountryItem country, View view) {
        String toolbarTitle = getString(R.string.country_details_activity_title_constant_part);

        if (country != null) {
            toolbarTitle += country.getName();
        }

        initToolbarWithBackButton(view, toolbarTitle);
    }
}
