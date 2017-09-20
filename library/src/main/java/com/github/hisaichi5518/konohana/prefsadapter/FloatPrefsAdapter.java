package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class FloatPrefsAdapter {
    public static float get(SharedPreferences prefs, @NonNull String key, float defaultValue) {
        return prefs.getFloat(key, defaultValue);
    }

    public static void set(SharedPreferences prefs, @NonNull String key, float value) {
        prefs.edit().putFloat(key, value).apply();
    }
}
