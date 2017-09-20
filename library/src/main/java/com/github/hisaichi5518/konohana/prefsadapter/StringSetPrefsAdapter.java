package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.Set;

public class StringSetPrefsAdapter {
    public static Set<String> get(SharedPreferences prefs, @NonNull String key, @NonNull Set<String> defaultValue) {
        return prefs.getStringSet(key, defaultValue);
    }

    public static void set(SharedPreferences prefs, @NonNull String key, @NonNull Set<String> value) {
        prefs.edit().putStringSet(key, value).apply();
    }
}
