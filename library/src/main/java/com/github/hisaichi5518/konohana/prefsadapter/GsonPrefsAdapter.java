package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

public class GsonPrefsAdapter {
    private static final Gson GSON = new Gson();

    @SafeVarargs
    public static <T> T get(SharedPreferences prefs, @NonNull String key, @Nullable T defaultValue, T... types) {
        String val = prefs.getString(key, null);
        if (val == null) {
            return defaultValue;
        }

        @SuppressWarnings("unchecked")
        Class<T> klass = (Class<T>) types.getClass().getComponentType();
        return GSON.fromJson(val, klass);
    }

    public static <T> void set(SharedPreferences prefs, @NonNull String key, @Nullable T value) {
        prefs.edit().putString(key, value == null ? null : GSON.toJson(value)).apply();
    }
}
