package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Set;

public class StringSetPrefsAdapter {
    @Nullable
    public static Set<String> get(SharedPreferences prefs, @NonNull String key, @Nullable Set<String> defaultValue) {
        return prefs.getStringSet(key, defaultValue);
    }

    public static void set(SharedPreferences prefs, @NonNull String key, @Nullable Set<String> value) {
        prefs.edit().putStringSet(key, value).apply();
    }
}
