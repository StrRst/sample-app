package com.example.sampleapp.base;

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();
}
