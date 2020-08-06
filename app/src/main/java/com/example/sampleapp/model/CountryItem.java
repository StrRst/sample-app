package com.example.sampleapp.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.sampleapp.database.CountryLanguageListConverter;
import com.example.sampleapp.database.UriConverter;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Locale;

@Entity(tableName = "countries")
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


    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String region;
    private String nativeName;
    private String capital;
    private int population;
    private double area;

    @TypeConverters({CountryLanguageListConverter.class})
    private List<CountryLanguage> languages;

    @TypeConverters({UriConverter.class})
    @SerializedName("flag")
    private Uri flagUrl;

    public CountryItem(String name, String region, String nativeName, String capital, int population, double area, List<CountryLanguage> languages, Uri flagUrl) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
            result.append(language.getName()).append("\n");
        }
        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }

        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryItem that = (CountryItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
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
