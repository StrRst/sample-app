package com.example.sampleapp.presenter;

import com.example.sampleapp.contract.MainContainerContract;
import com.example.sampleapp.model.CountryItem;

public class MainContainerPresenter implements MainContainerContract.Presenter {

    private MainContainerContract.View view;

    @Override
    public void takeView(MainContainerContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    @Override
    public void onCountrySelect(CountryItem country, boolean isLandscapeOrientation) {
        if (isLandscapeOrientation) {
            view.showCountryDetails(country);
        } else {
            view.startDetailsActivity(country);
        }
    }

    @Override
    public void onProgressStateChanged(boolean isActive) {
        if (isActive) {
            view.showProgress();
        } else {
            view.hideProgress();
        }
    }

    @Override
    public void onHistoryMenuItemClick() {
        view.startSearchHistoryActivity();
    }
}
