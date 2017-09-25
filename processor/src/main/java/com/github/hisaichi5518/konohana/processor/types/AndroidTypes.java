package com.github.hisaichi5518.konohana.processor.types;

import com.squareup.javapoet.ClassName;

public class AndroidTypes {
    public static final ClassName SharedPreferences = ClassName.get("android.content", "SharedPreferences");

    public static final ClassName Context = ClassName.get("android.content", "Context");

    public static final ClassName TextUtils = ClassName.get("android.text", "TextUtils");
}
