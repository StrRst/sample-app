package com.example.sampleapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.sampleapp.api.RestClient;
import com.example.sampleapp.app.App;
import com.example.sampleapp.model.HistoryItems;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ApplicationSharedPrefsManager {

    private static Context context = App.getInstance();

    private static SharedPreferences getPrefs() {
        return context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void saveHistoryItems(HistoryItems items) {
        getPrefs().edit()
                .putString(Constants.PREFS_HISTORY_ITEMS, RestClient.getInstance()
                        .getGson()
                        .toJson(items))
                .apply();
    }

    public static HistoryItems retrieveHistoryItems() {
        Type listType = new TypeToken<HistoryItems>() {
        }.getType();
        String jsonList = getPrefs().getString(Constants.PREFS_HISTORY_ITEMS, null);
        if (TextUtils.isEmpty(jsonList)) {
            return new HistoryItems();
        }
        return RestClient.getInstance().getGson().fromJson(jsonList, listType);
    }

    public static void clearHistoryItems() {
        saveHistoryItems(new HistoryItems());
    }
}
