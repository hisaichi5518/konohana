package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class BooleanPrefsAdapter {
    public static boolean get(SharedPreferences prefs, @NonNull String key, boolean defaultValue) {
        return prefs.getBoolean(key, defaultValue);
    }

    public static void set(SharedPreferences prefs, @NonNull String key, boolean value) {
        prefs.edit().putBoolean(key, value).apply();
    }
}
