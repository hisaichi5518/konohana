package com.github.hisaichi5518.konohana.processor.types;

import com.squareup.javapoet.ClassName;

public class KonohanaTypes {
    private static final String PACKAGE = "com.github.hisaichi5518.konohana";

    private static final String PREFS_ADAPTER_PACKAGE = PACKAGE + ".prefsadapter";

    public static final ClassName BooleanPrefsAdapter = ClassName.get(PREFS_ADAPTER_PACKAGE, "BooleanPrefsAdapter");

    public static final ClassName EnumPrefsAdapter = ClassName.get(PREFS_ADAPTER_PACKAGE, "EnumPrefsAdapter");

    public static final ClassName FloatPrefsAdapter = ClassName.get(PREFS_ADAPTER_PACKAGE, "FloatPrefsAdapter");

    public static final ClassName GsonPrefsAdapter = ClassName.get(PREFS_ADAPTER_PACKAGE, "GsonPrefsAdapter");

    public static final ClassName IntegerPrefsAdapter = ClassName.get(PREFS_ADAPTER_PACKAGE, "IntegerPrefsAdapter");

    public static final ClassName ListPrefsAdapter = ClassName.get(PREFS_ADAPTER_PACKAGE, "ListPrefsAdapter");

    public static final ClassName LongPrefsAdapter = ClassName.get(PREFS_ADAPTER_PACKAGE, "LongPrefsAdapter");

    public static final ClassName SetPrefsAdapter = ClassName.get(PREFS_ADAPTER_PACKAGE, "SetPrefsAdapter");

    public static final ClassName StringPrefsAdapter = ClassName.get(PREFS_ADAPTER_PACKAGE, "StringPrefsAdapter");

    public static final ClassName StringSetPrefsAdapter = ClassName.get(PREFS_ADAPTER_PACKAGE, "StringSetPrefsAdapter");

    public static final ClassName UseBuildInPrefsAdapter = ClassName.get(PACKAGE + ".annotation", "Key", "UseBuildInPrefsAdapter");

    public static final ClassName OnPreferenceChangeListener = ClassName.get(PACKAGE + ".listener", "OnPreferenceChangeListener");
}
