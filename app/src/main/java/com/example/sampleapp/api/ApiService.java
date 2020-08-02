package com.example.sampleapp.api;

import com.example.sampleapp.model.CountryItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("rest/v2/name/{name}")
    Call<List<CountryItem>> getCountries(@Path("name") String countryName);
}
