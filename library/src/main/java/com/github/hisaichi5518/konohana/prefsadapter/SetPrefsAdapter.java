package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.Type;
import java.util.Set;

public class SetPrefsAdapter {
    private static final Gson GSON = new Gson();

    @SafeVarargs
    @Nullable
    // http://d.hatena.ne.jp/skirnir/20090224/1235483079
    public static <T> Set<T> get(SharedPreferences prefs, @NonNull String key, @Nullable Set<T> defaultValue, T... types) {
        String val = prefs.getString(key, null);
        if (val == null) {
            return defaultValue;
        }

        // http://gfx.hatenablog.com/entry/2015/08/28/132304
        @SuppressWarnings("unchecked")
        Type type = $Gson$Types.newParameterizedTypeWithOwner(null, Set.class, (Class<T>) types.getClass().getComponentType());
        return GSON.fromJson(val, type);
    }

    public static <T> void set(SharedPreferences prefs, @NonNull String key, @Nullable T value) {
        prefs.edit().putString(key, value == null ? null : GSON.toJson(value)).apply();
    }
}
