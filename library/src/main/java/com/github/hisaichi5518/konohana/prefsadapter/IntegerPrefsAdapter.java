package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class IntegerPrefsAdapter {
    public static int get(SharedPreferences prefs, @NonNull String key, int defaultValue) {
        return prefs.getInt(key, defaultValue);
    }

    public static void set(SharedPreferences prefs, @NonNull String key, int value) {
        prefs.edit().putInt(key, value).apply();
    }
}
