package com.example.sampleapp.contract;

import com.example.sampleapp.base.BasePresenter;
import com.example.sampleapp.base.BaseView;
import com.example.sampleapp.model.CountryItem;

public interface MainContainerContract {

    interface View extends BaseView<Presenter> {

        void showProgress();

        void hideProgress();

        void startSearchHistoryActivity();

        void startDetailsActivity(CountryItem country);

        void showCountryDetails(CountryItem country);
    }

    interface Presenter extends BasePresenter<View> {

        void onCountrySelect(CountryItem country, boolean isLandscapeOrientation);

        void onProgressStateChanged(boolean isActive);

        void onHistoryMenuItemClick();
    }
}
