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
import com.example.sampleapp.contract.MainContainerContract;
import com.example.sampleapp.model.CountryItem;
import com.example.sampleapp.presenter.ChooserPresenter;
import com.example.sampleapp.util.Constants;

public class MainContainerFragment extends BaseFragment implements MainContainerContract.View {

    private MainContainerContract.Presenter presenter;

    private FragmentContainerView chooserFragmentContainer;
    private FragmentContainerView viewerFragmentContainer;

    private ChooserFragment chooserFragment;
    private ViewerFragment viewerFragment;

    private View loadingBlock;

    public MainContainerFragment() {

    }

    @Override
    public void setPresenter(MainContainerContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_container, container, false);

        chooserFragmentContainer = view.findViewById(R.id.chooser_fragment_container);
        viewerFragmentContainer = view.findViewById(R.id.viewer_fragment_container);
        loadingBlock = view.findViewById(R.id.loading_block);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initToolbar(view, getString(R.string.main_activity_title));
        initToolbarMenu();

        initChooserFragment();

        if (inLandscapeMode) {
            initViewerFragment();
        }

        chooserFragment.setCountrySelectListener(country -> {
            presenter.onCountrySelect(country, inLandscapeMode);
        });

        chooserFragment.setProgressStateChangeListener(isActive -> {
            presenter.onProgressStateChanged(isActive);
        });

        presenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    private void initToolbarMenu() {
        Toolbar toolbar = getToolbar();
        toolbar.inflateMenu(R.menu.main_activity_menu);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_show_search_history:
                    presenter.onHistoryMenuItemClick();
                    return true;
                default:
                    return false;
            }
        });
    }

    private void initChooserFragment() {
        chooserFragment = (ChooserFragment) getChildFragmentManager()
                .findFragmentById(chooserFragmentContainer.getId());

        if (chooserFragment == null) {
            chooserFragment = new ChooserFragment();

            Intent intent = getActivity().getIntent();
            if (intent.hasExtra(Constants.SEARCH_STRING)) {
                chooserFragment.setSearchString(intent.getStringExtra(Constants.SEARCH_STRING));
            }

            chooserFragment.setPresenter(new ChooserPresenter());

            getChildFragmentManager().beginTransaction()
                    .add(chooserFragmentContainer.getId(), chooserFragment)
                    .commit();
        } else {
            chooserFragment.setPresenter(new ChooserPresenter());
        }
    }

    private void initViewerFragment() {
        viewerFragment = (ViewerFragment) getChildFragmentManager()
                .findFragmentById(viewerFragmentContainer.getId());

        if (viewerFragment == null) {
            viewerFragment = new ViewerFragment();
            getChildFragmentManager().beginTransaction()
                    .add(viewerFragmentContainer.getId(), viewerFragment)
                    .commit();
        }
    }

    @Override
    public void showProgress() {
        if (loadingBlock != null) {
            loadingBlock.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (loadingBlock != null) {
            loadingBlock.setVisibility(View.GONE);
        }
    }

    @Override
    public void startSearchHistoryActivity() {
        Intent intent = new Intent(getActivity(), SearchHistoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void startDetailsActivity(CountryItem country) {
        Intent intent = new Intent(getActivity(), CountryDetailsActivity.class);
        intent.putExtra(Constants.COUNTRY_OBJECT, country);
        startActivity(intent);
    }

    @Override
    public void showCountryDetails(CountryItem country) {
        if (viewerFragment != null) {
            viewerFragment.setData(country);
        }
    }
}
