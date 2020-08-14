package com.example.sampleapp.contract;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.sampleapp.base.BasePresenter;
import com.example.sampleapp.base.BaseView;
import com.example.sampleapp.model.CountryItem;

import java.util.List;

public interface ChooserContract {

    interface View extends BaseView<ChooserContract.Presenter> {

        void showProgress();

        void hideProgress();

        void hideKeyboard();

        void setInputFieldText(String text);

        void setFocusOnContent();

        void displayInputError();

        void displayRequestError(@NonNull String message);

        void observeData(LiveData<List<CountryItem>> countryList);

        void stopObserving(LiveData<List<CountryItem>> countryList);
    }

    interface Presenter extends BasePresenter<ChooserContract.View> {

        void handleInitiateSearch(@NonNull String inputFieldText);

        void handleRestoreSearchFromHistory(@NonNull String searchString);
    }
}
