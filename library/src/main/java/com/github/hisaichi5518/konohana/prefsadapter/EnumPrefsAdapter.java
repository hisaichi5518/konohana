package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class EnumPrefsAdapter {
    public static <T extends Enum<T>> T get(SharedPreferences prefs, @NonNull String key, @NonNull T defaultValue) {
        String val = prefs.getString(key, null);
        if (val == null) {
            return defaultValue;
        }

        return Enum.valueOf(defaultValue.getDeclaringClass(), val);
    }

    public static <T extends Enum<T>> void set(SharedPreferences prefs, @NonNull String key, @NonNull T value) {
        prefs.edit().putString(key, value.name()).apply();
    }
}
