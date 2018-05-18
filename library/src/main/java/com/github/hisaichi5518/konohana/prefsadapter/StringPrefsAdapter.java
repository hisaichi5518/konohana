package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class StringPrefsAdapter {
    @Nullable
    public static String get(SharedPreferences prefs, @NonNull String key, @Nullable String defaultValue) {
        return prefs.getString(key, defaultValue);
    }

    public static void set(SharedPreferences prefs, @NonNull String key, @Nullable String value) {
        prefs.edit().putString(key, value).apply();
    }
}
