package com.example.sampleapp.model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class CountryItem {

    private String name;
    private String region;

    @SerializedName("flag")
    private Uri flagUrl;

    public CountryItem(String name, String region) {
        this.name = name;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Uri getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(Uri flagUrl) {
        this.flagUrl = flagUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryItem that = (CountryItem) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(region, that.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, region);
    }
}
