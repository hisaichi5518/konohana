package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

public class GsonPrefsAdapter {
    private static final Gson GSON = new Gson();

    public static <T> T get(SharedPreferences prefs, @NonNull String key, @NonNull T defaultValue) {
        String val = prefs.getString(key, null);
        if (val == null) {
            return defaultValue;
        }
        return (T) GSON.fromJson(val, defaultValue.getClass());
    }

    public static <T> void set(SharedPreferences prefs, @NonNull String key, @NonNull T value) {
        prefs.edit().putString(key, GSON.toJson(value)).apply();
    }
}
