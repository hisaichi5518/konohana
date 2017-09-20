package com.github.hisaichi5518.konohana.processor.utils;

import android.support.annotation.NonNull;

public class Strings {
    @NonNull
    public static String upperFirst(@NonNull String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
