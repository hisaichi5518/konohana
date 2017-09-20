package com.github.hisaichi5518.konohana.processor.builder;

import com.github.hisaichi5518.konohana.processor.definition.KeyDefinition;
import com.github.hisaichi5518.konohana.processor.definition.StoreDefinition;
import com.github.hisaichi5518.konohana.processor.types.AnnotationTypes;
import com.github.hisaichi5518.konohana.processor.types.KonohanaTypes;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

public class StoreMethods {
    public static List<MethodSpec> build(StoreDefinition storeDefinition) {
        List<MethodSpec> specs = new ArrayList<>();

        specs.add(buildRemoveAllMethod());

        storeDefinition.keyDefinitionStream().forEach(keyDefinition -> {
            specs.add(buildGetterSpec(keyDefinition));
            specs.add(buildSetterSpec(keyDefinition));
            specs.add(buildContainsSpec(keyDefinition));
            specs.add(buildRemoverSpec(keyDefinition));
        });

        return specs;
    }

    private static MethodSpec buildGetterSpec(KeyDefinition keyDefinition) {
        return MethodSpec.methodBuilder(keyDefinition.getGetterName())
                .returns(keyDefinition.getFieldTypeName())
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationTypes.NonNull)
                .addStatement("return $T.get(prefs, $S, $L)",
                        /* TODO */ KonohanaTypes.StringPrefsAdapter, keyDefinition.getPrefsKeyName(), keyDefinition.getFieldName())
                .build();
    }

    private static MethodSpec buildSetterSpec(KeyDefinition keyDefinition) {
        return MethodSpec.methodBuilder(keyDefinition.getSetterName())
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(keyDefinition.getFieldTypeName(), "value").addAnnotation(AnnotationTypes.NonNull).build())
                .addStatement("$T.set(prefs, $S, value)",
                        /* TODO */ KonohanaTypes.StringPrefsAdapter, keyDefinition.getPrefsKeyName())
                .build();

    }

    private static MethodSpec buildContainsSpec(KeyDefinition keyDefinition) {
        return MethodSpec.methodBuilder(keyDefinition.getContainsName())
                .returns(boolean.class)
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return prefs.contains($S)", keyDefinition.getPrefsKeyName())
                .build();
    }

    private static MethodSpec buildRemoverSpec(KeyDefinition keyDefinition) {
        return MethodSpec.methodBuilder(keyDefinition.getRemoverName())
                .addModifiers(Modifier.PUBLIC)
                .addStatement("prefs.edit().remove($S).apply()", keyDefinition.getPrefsKeyName())
                .build();
    }

    private static MethodSpec buildRemoveAllMethod() {
        return MethodSpec.methodBuilder("removeAll")
                .addModifiers(Modifier.PUBLIC)
                .addStatement("prefs.edit().clear().apply()")
                .build();
    }
}
