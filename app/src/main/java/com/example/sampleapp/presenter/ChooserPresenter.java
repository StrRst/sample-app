package com.example.sampleapp.presenter;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.sampleapp.api.ApiCallback;
import com.example.sampleapp.api.RestClient;
import com.example.sampleapp.app.App;
import com.example.sampleapp.contract.ChooserContract;
import com.example.sampleapp.database.AppDatabase;
import com.example.sampleapp.model.CountryErrorItem;
import com.example.sampleapp.model.CountryItem;
import com.example.sampleapp.model.HistoryItems;
import com.example.sampleapp.util.ApplicationSharedPrefsManager;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Response;

public class ChooserPresenter implements ChooserContract.Presenter {

    private static final String TAG = ChooserPresenter.class.getSimpleName();

    private ChooserContract.View view;
    private LiveData<List<CountryItem>> liveCountryList;
    private HistoryItems historyItems;

    @Override
    public void takeView(ChooserContract.View view) {
        this.view = view;

        historyItems = ApplicationSharedPrefsManager.retrieveHistoryItems();

        liveCountryList = getDatabase().countryItemDao().getAll();
        view.observeData(liveCountryList);
    }

    @Override
    public void dropView() {
        view.stopObserving(liveCountryList);

        view = null;
        liveCountryList = null;
        historyItems = null;
    }

    @Override
    public void handleInitiateSearch(@NonNull String inputFieldText) {
        if (TextUtils.isEmpty(inputFieldText)) {
            view.displayInputError();
            return;
        }

        view.hideKeyboard();
        view.setFocusOnContent();

        historyItems.addToStart(inputFieldText);
        ApplicationSharedPrefsManager.saveHistoryItems(historyItems);

        searchCountries(inputFieldText, errorItem -> {
            view.hideProgress();
            if (errorItem != null) {
                String errorMessage = errorItem.getMessage();
                view.displayRequestError(errorMessage);
                Log.e(TAG, errorMessage);
            }
        });
    }

    @Override
    public void handleRestoreSearchFromHistory(@NonNull String searchString) {
        if (TextUtils.isEmpty(searchString)) {
            view.displayInputError();
            return;
        }

        view.setFocusOnContent();

        view.setInputFieldText(searchString);

        clearCountryList();

        searchCountries(searchString, errorItem -> {
            view.hideProgress();
            if (errorItem != null) {
                String errorMessage = errorItem.getMessage();
                view.displayRequestError(errorMessage);
                Log.e(TAG, errorMessage);
            }
        });
    }

    private void searchCountries(String countryName, OnRequestFinishedListener requestFinishedListener) {
        view.showProgress();

        RestClient.getInstance().getService().getCountries(countryName)
                .enqueue(new ApiCallback<List<CountryItem>>() {
                    @Override
                    public void success(@NotNull Response<List<CountryItem>> response) {
                        replaceCountryList(response.body());

                        if (requestFinishedListener != null) {
                            requestFinishedListener.onRequestFinished(null);
                        }
                    }

                    @Override
                    public void failure(CountryErrorItem countryError) {
                        if (requestFinishedListener != null) {
                            requestFinishedListener.onRequestFinished(countryError);
                        }
                    }
                });

    }

    private void clearCountryList() {
        getDatabase().countryItemDao().deleteAll();
    }

    private void replaceCountryList(List<CountryItem> newList) {
        getDatabase().countryItemDao().deleteAll();
        getDatabase().countryItemDao().insert(newList);
    }

    private AppDatabase getDatabase() {
        return App.getInstance().getDatabase();
    }

    interface OnRequestFinishedListener {

        void onRequestFinished(@Nullable CountryErrorItem errorItem);
    }
}
