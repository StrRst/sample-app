package com.example.sampleapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.sampleapp.api.RestClient;
import com.example.sampleapp.model.HistoryItems;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class HistorySharedPrefsUtils {

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void saveHistoryItems(Context context, HistoryItems items) {
        getPrefs(context).edit()
                .putString(Constants.PREFS_HISTORY_ITEMS, RestClient.getInstance()
                        .getGson()
                        .toJson(items))
                .apply();
    }

    public static void clearHistoryItems(Context context) {
        saveHistoryItems(context, new HistoryItems());
    }

    public static HistoryItems retrieveHistoryItems(Context context) {
        Type listType = new TypeToken<HistoryItems>() {
        }.getType();
        String jsonList = getPrefs(context).getString(Constants.PREFS_HISTORY_ITEMS, null);
        if (TextUtils.isEmpty(jsonList)) {
            return new HistoryItems();
        }
        return RestClient.getInstance().getGson().fromJson(jsonList, listType);
    }
}
