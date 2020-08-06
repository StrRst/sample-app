package com.example.sampleapp.api;

import com.example.sampleapp.R;
import com.example.sampleapp.app.App;
import com.example.sampleapp.model.CountryErrorItem;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<T> {

    public abstract void success(Response<T> response);

    public abstract void failure(CountryErrorItem countryError);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            success(response);
        } else {
            Converter<ResponseBody, CountryErrorItem> converter = RestClient.getInstance()
                    .getRetrofit()
                    .responseBodyConverter(CountryErrorItem.class, new Annotation[0]);

            try {
                CountryErrorItem countryError = converter.convert(response.errorBody());
                failure(countryError);
            } catch (Exception e) {
                failure(new CountryErrorItem(App.getInstance()
                        .getString(R.string.api_callback_unhandled_error) + response.code()));
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        failure(new CountryErrorItem(App.getInstance()
                .getString(R.string.api_callback_unexpected_error) + t.getMessage()));
    }
}
