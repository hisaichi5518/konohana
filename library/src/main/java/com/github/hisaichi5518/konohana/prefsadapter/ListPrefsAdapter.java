package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.Type;
import java.util.List;

public class ListPrefsAdapter {
    private static final Gson GSON = new Gson();

    // http://d.hatena.ne.jp/skirnir/20090224/1235483079
    public static <T> List<T> get(SharedPreferences prefs, @NonNull String key, @NonNull List<T> defaultValue, T... types) {
        String val = prefs.getString(key, null);
        if (val == null) {
            return defaultValue;
        }

        @SuppressWarnings("unchecked")
        Type type = $Gson$Types.newParameterizedTypeWithOwner(null, List.class, (Class<T>) types.getClass().getComponentType());
        return GSON.fromJson(val, type);
    }

    public static <T> void set(SharedPreferences prefs, @NonNull String key, @NonNull T value) {
        prefs.edit().putString(key, GSON.toJson(value)).apply();
    }
}
