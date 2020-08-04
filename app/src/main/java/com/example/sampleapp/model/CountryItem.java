package com.example.sampleapp.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class CountryItem implements Parcelable {

    public static final Creator<CountryItem> CREATOR = new Creator<CountryItem>() {
        @Override
        public CountryItem createFromParcel(Parcel in) {
            return new CountryItem(in);
        }

        @Override
        public CountryItem[] newArray(int size) {
            return new CountryItem[size];
        }
    };

    private String name;
    private String region;
    private String nativeName;
    private String capital;
    private int population;
    private double area;
    private List<CountryLanguage> languages;

    @SerializedName("flag")
    private Uri flagUrl;

    public CountryItem(String name, String region, String nativeName, String capital, int population, int area, List<CountryLanguage> languages, Uri flagUrl) {
        this.name = name;
        this.region = region;
        this.nativeName = nativeName;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.languages = languages;
        this.flagUrl = flagUrl;
    }

    protected CountryItem(Parcel in) {
        name = in.readString();
        region = in.readString();
        nativeName = in.readString();
        capital = in.readString();
        population = in.readInt();
        area = in.readDouble();
        languages = in.createTypedArrayList(CountryLanguage.CREATOR);
        flagUrl = in.readParcelable(Uri.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(region);
        dest.writeString(nativeName);
        dest.writeString(capital);
        dest.writeInt(population);
        dest.writeDouble(area);
        dest.writeTypedList(languages);
        dest.writeParcelable(flagUrl, flags);
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public List<CountryLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(List<CountryLanguage> languages) {
        this.languages = languages;
    }

    public Uri getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(Uri flagUrl) {
        this.flagUrl = flagUrl;
    }

    public String getPopulationAsString() {
        if (population < 1000) {
            return Integer.toString(population);
        }
        if (population < 1000000) {
            return String.format(Locale.US, "%.2f thou", (double) population / 1000);
        }
        if (population < 1000000000) {
            return String.format(Locale.US, "%.2f mil", (double) population / 1000000);
        }
        return String.format(Locale.US, "%.2f bil", (double) population / 1000000000);
    }

    public String getAreaAsString() {
        if (area == (int) area) {
            return (int) area + " km²";
        }
        return String.format(Locale.US, "%.2f km²", area);
    }

    public String getLanguagesAsString() {
        StringBuilder result = new StringBuilder();

        for (CountryLanguage language : languages) {
            result.append("\n").append(language.name);
        }
        if (result.length() > 0) {
            result.deleteCharAt(0);
        }

        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryItem that = (CountryItem) o;
        return population == that.population &&
                area == that.area &&
                Objects.equals(name, that.name) &&
                Objects.equals(region, that.region) &&
                Objects.equals(nativeName, that.nativeName) &&
                Objects.equals(capital, that.capital) &&
                Objects.equals(languages, that.languages) &&
                Objects.equals(flagUrl, that.flagUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, region, nativeName, capital, population, area, languages, flagUrl);
    }

    @Override
    public String toString() {
        return "CountryItem{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", nativeName='" + nativeName + '\'' +
                ", capital='" + capital + '\'' +
                ", population=" + population +
                ", area=" + area +
                ", languages=" + languages +
                ", flagUrl=" + flagUrl +
                '}';
    }
}
