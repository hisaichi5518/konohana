package com.github.hisaichi5518.konohana.processor.builder;

import com.github.hisaichi5518.konohana.processor.definition.StoreDefinition;
import com.squareup.javapoet.MethodSpec;

import java.util.Collections;
import java.util.List;

import javax.lang.model.element.Modifier;

public class StoreMethods {
    public static List<MethodSpec> build(StoreDefinition storeDefinition) {
        List<MethodSpec> specs = Collections.emptyList();

        specs.add(MethodSpec.methodBuilder("removeAll")
                .addModifiers(Modifier.PUBLIC)
                .addStatement("prefs.edit().clear().apply()")
                .build());

        return specs;
    }
}
