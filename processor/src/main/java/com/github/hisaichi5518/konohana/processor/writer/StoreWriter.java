package com.github.hisaichi5518.konohana.processor.writer;

import com.github.hisaichi5518.konohana.processor.definition.StoreDefinition;
import com.github.hisaichi5518.konohana.processor.types.AndroidTypes;
import com.github.hisaichi5518.konohana.processor.types.AnnotationTypes;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.Collections;
import java.util.List;

import javax.lang.model.element.Modifier;

public class StoreWriter {

    public static void write(StoreDefinition storeDefinition) {

        TypeSpec typeSpec = TypeSpec.classBuilder(storeDefinition.getStoreClassName())
                .addSuperinterface(storeDefinition.getInterfaceName())
                .addFields(buildFields(storeDefinition))
                .addMethods(buildConstructors(storeDefinition))
                .build();
    }

    private static List<FieldSpec> buildFields(StoreDefinition storeDefinition) {
        List<FieldSpec> specs = Collections.emptyList();

        specs.add(FieldSpec.builder(AndroidTypes.SharedPreferences, "prefs")
                .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
                .build());

        return specs;

    }

    private static List<MethodSpec> buildConstructors(StoreDefinition storeDefinition) {
        List<MethodSpec> specs = Collections.emptyList();

        specs.add(MethodSpec.constructorBuilder()
                .addParameter(ParameterSpec.builder(AndroidTypes.Context, "context").addAnnotation(AnnotationTypes.NonNull).build())
                .addStatement("this.prefs = context.getSharedPreferences($S, $L)", storeDefinition.getPrefsFileName(), storeDefinition.getPrefsMode())
                .build());

        return specs;
    }
}
