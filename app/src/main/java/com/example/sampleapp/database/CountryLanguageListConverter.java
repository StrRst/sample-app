package com.example.sampleapp.database;

import androidx.room.TypeConverter;

import com.example.sampleapp.model.CountryLanguage;

import java.util.ArrayList;
import java.util.List;

public class CountryLanguageListConverter {

    @TypeConverter
    public String fromCountryLanguageList(List<CountryLanguage> countryLanguageList) {
        StringBuilder encodedList = new StringBuilder();

        for (CountryLanguage language : countryLanguageList) {
            encodedList.append(language.getName()).append(",");
        }
        if (encodedList.length() > 0) {
            encodedList.setLength(encodedList.length() - 1);
        }

        return encodedList.toString();
    }

    @TypeConverter
    public List<CountryLanguage> toCountryLanguageList(String encodedList) {
        List<CountryLanguage> countryLanguages = new ArrayList<>();

        String[] languages = encodedList.split(",");
        for (String language : languages) {
            countryLanguages.add(new CountryLanguage(language));
        }

        return countryLanguages;
    }
}
