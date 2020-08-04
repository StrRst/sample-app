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

    String name;
    String nativeName;

    public CountryLanguage(String name, String nativeName) {
        this.name = name;
        this.nativeName = nativeName;
    }

    protected CountryLanguage(Parcel in) {
        name = in.readString();
        nativeName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(nativeName);
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

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryLanguage that = (CountryLanguage) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(nativeName, that.nativeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nativeName);
    }

    @Override
    public String toString() {
        return "CountryLanguage{" +
                "name='" + name + '\'' +
                ", nativeName='" + nativeName + '\'' +
                '}';
    }
}
