package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class LongPrefsAdapter {
    public static long get(SharedPreferences prefs, @NonNull String key, long defaultValue) {
        return prefs.getLong(key, defaultValue);
    }

    public static void set(SharedPreferences prefs, @NonNull String key, long value) {
        prefs.edit().putLong(key, value).apply();
    }
}
