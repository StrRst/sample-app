package com.example.sampleapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class CountryLanguage implements Parcelable {

    public static final Creator<CountryLanguage> CREATOR = new Creator<CountryLanguage>() {
        @Override
        public CountryLanguage createFromParcel(Parcel in) {
            return new CountryLanguage(in);
        }

        @Override
        public CountryLanguage[] newArray(int size) {
            return new CountryLanguage[size];
        }
    };

    private String name;

    public CountryLanguage(String name) {
        this.name = name;
    }

    protected CountryLanguage(Parcel in) {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryLanguage language = (CountryLanguage) o;
        return Objects.equals(name, language.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "CountryLanguage{" +
                "name='" + name + '\'' +
                '}';
    }
}
