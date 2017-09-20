package com.github.hisaichi5518.konohana.processor.types;

import com.squareup.javapoet.ClassName;

public class KonohanaTypes {
    private static final String PACKAGE = "com.github.hisaichi5518.konohana";

    private static final String PREFS_ADAPTER_PACKAGE = PACKAGE + ".prefsadapter";

    public static final ClassName StringPrefsAdapter = ClassName.get(PREFS_ADAPTER_PACKAGE, "StringPrefsAdapter");
}
