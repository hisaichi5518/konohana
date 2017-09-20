package com.github.hisaichi5518.konohana.processor.writer;

import com.github.hisaichi5518.konohana.processor.definition.StoreDefinition;
import com.github.hisaichi5518.konohana.processor.types.AndroidTypes;
import com.github.hisaichi5518.konohana.processor.types.AnnotationTypes;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;

public class StoreWriter {

    public static void write(StoreDefinition storeDefinition) {

        TypeSpec typeSpec = TypeSpec.classBuilder(storeDefinition.getStoreClassName())
                .addSuperinterface(storeDefinition.getInterfaceName())
                .addField(buildField())
                .addMethod(buildConstructor(storeDefinition))
                .build();
    }

    private static FieldSpec buildField() {
        return FieldSpec.builder(AndroidTypes.SharedPreferences, "prefs")
                .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
                .build();
    }

    private static MethodSpec buildConstructor(StoreDefinition storeDefinition) {
        return MethodSpec.constructorBuilder()
                .addParameter(ParameterSpec.builder(AndroidTypes.Context, "context").addAnnotation(AnnotationTypes.NonNull).build())
                .addStatement("this.prefs = context.getSharedPreferences($S, $L)", storeDefinition.getPrefsFileName(), storeDefinition.getPrefsMode())
                .build();
    }
}
