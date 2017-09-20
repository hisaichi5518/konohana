package com.github.hisaichi5518.konohana.processor.writer;

import com.github.hisaichi5518.konohana.processor.definition.StoreDefinition;
import com.github.hisaichi5518.konohana.processor.types.AndroidTypes;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.Collections;
import java.util.List;

import javax.lang.model.element.Modifier;

public class StoreWriter {

    public static void write(StoreDefinition storeDefinition) {

        TypeSpec typeSpec = TypeSpec.classBuilder(storeDefinition.getStoreClassName())
                .addSuperinterface(storeDefinition.getInterfaceName())
                .addFields(buildFields(storeDefinition))
                .build();
    }

    private static List<FieldSpec> buildFields(StoreDefinition storeDefinition) {
        List<FieldSpec> specs = Collections.emptyList();

        specs.add(FieldSpec.builder(AndroidTypes.SharedPreferences, "prefs")
                .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
                .build());

        return specs;

    }
}
