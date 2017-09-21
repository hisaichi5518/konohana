package com.github.hisaichi5518.konohana.processor.model;

import android.support.annotation.Nullable;

import com.github.hisaichi5518.konohana.processor.definition.KeyDefinition;
import com.github.hisaichi5518.konohana.processor.types.JavaTypes;
import com.github.hisaichi5518.konohana.processor.types.KonohanaTypes;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import java.util.stream.Stream;

public class PrefsAdapter {

    private static final PrefsAdapter[] BUILD_IN = {
            create(TypeName.INT, KonohanaTypes.IntegerPrefsAdapter),
            create(TypeName.BOOLEAN, KonohanaTypes.BooleanPrefsAdapter),
            create(TypeName.FLOAT, KonohanaTypes.FloatPrefsAdapter),
            create(TypeName.LONG, KonohanaTypes.LongPrefsAdapter),

            create(JavaTypes.String, KonohanaTypes.StringPrefsAdapter),
            create(JavaTypes.getSet(JavaTypes.String), KonohanaTypes.StringSetPrefsAdapter)
    };

    private final TypeName target;
    private final ClassName adapter;

    private static PrefsAdapter create(TypeName target, ClassName adapter) {
        return new PrefsAdapter(target, adapter);
    }

    @Nullable
    public static TypeName find(KeyDefinition keyDefinition) {
        TypeName customPrefsAdapterType = keyDefinition.getCustomPrefsAdapterType();
        if (customPrefsAdapterType.equals(KonohanaTypes.UseBuildInPrefsAdapter)) {
            // Use BuildIn Adapter
            return Stream.of(BUILD_IN)
                    .filter(prefsAdapter -> prefsAdapter.match(keyDefinition.getFieldTypeName()))
                    .map(prefsAdapter -> prefsAdapter.adapter)
                    .findFirst()
                    .orElseGet(() -> {
                        if (keyDefinition.isEnum()) {
                            return KonohanaTypes.EnumPrefsAdapter;
                        } else if (keyDefinition.isList()) {
                            return KonohanaTypes.ListPrefsAdapter;
                        } else if (keyDefinition.isSet()) {
                            return KonohanaTypes.SetPrefsAdapter;
                        } else {
                            return KonohanaTypes.GsonPrefsAdapter;
                        }
                    });
        } else {
            return customPrefsAdapterType;
        }
    }

    private boolean match(TypeName typeName) {
        return target.equals(typeName);
    }

    private PrefsAdapter(TypeName target, ClassName adapter) {
        this.target = target;
        this.adapter = adapter;
    }
}
