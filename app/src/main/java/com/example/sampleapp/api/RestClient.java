package com.example.sampleapp.api;

import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private final static String API_URL = "https://restcountries.eu/";

    private static RestClient sInstance = new RestClient();

    private Gson gson;
    private Retrofit retrofit;
    private ApiService service;

    private RestClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        gson = new GsonBuilder()
                .registerTypeAdapter(Uri.class, new UriDeserializer())
                .create();

        retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(API_URL)
                .build();

        service = retrofit.create(ApiService.class);
    }

    public static RestClient getInstance() {
        return sInstance;
    }

    public Gson getGson() {
        return gson;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public ApiService getService() {
        return service;
    }
}
