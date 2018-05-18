package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class EnumPrefsAdapter {

    @SafeVarargs
    @Nullable
    public static <T extends Enum<T>> T get(SharedPreferences prefs, @NonNull String key, @Nullable T defaultValue, T... types) {
        String val = prefs.getString(key, null);
        if (val == null) {
            return defaultValue;
        }

        @SuppressWarnings("unchecked")
        Class<T> klass = (Class<T>) types.getClass().getComponentType();
        return Enum.valueOf(klass, val);
    }

    public static <T extends Enum<T>> void set(SharedPreferences prefs, @NonNull String key, @Nullable T value) {
        if (value == null) {
            prefs.edit().putString(key, null).apply();
        } else {
            prefs.edit().putString(key, value.name()).apply();
        }
    }
}
