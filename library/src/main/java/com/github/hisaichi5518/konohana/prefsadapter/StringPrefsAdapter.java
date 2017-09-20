package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class StringPrefsAdapter {
    @NonNull
    public static String get(SharedPreferences prefs, @NonNull String key, @NonNull String defaultValue) {
        return prefs.getString(key, defaultValue);
    }

    public static void set(SharedPreferences prefs, @NonNull String key, @NonNull String value) {
        prefs.edit().putString(key, value).apply();
    }
}
