package com.github.hisaichi5518.konohana.processor.writer;

import android.support.annotation.NonNull;

import com.github.hisaichi5518.konohana.processor.definition.StoreDefinition;
import com.github.hisaichi5518.konohana.processor.types.AndroidTypes;
import com.github.hisaichi5518.konohana.processor.types.AnnotationTypes;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.lang.model.element.Modifier;

public class StoreWriter {

    public static void write(@NonNull StoreDefinition storeDefinition) {
        try {
            JavaFile.builder(storeDefinition.getInterfaceName().packageName(), buildTypeSpec(storeDefinition))
                    .build()
                    .writeTo(storeDefinition.getFiler());
        } catch (IOException e) {
            throw storeDefinition.newProcessingException(e);
        }
    }

    @NonNull
    private static TypeSpec buildTypeSpec(@NonNull StoreDefinition storeDefinition) {
        return TypeSpec.classBuilder(storeDefinition.getStoreClassName())
                .addSuperinterface(storeDefinition.getInterfaceName())
                .addField(buildField())
                .addMethod(buildConstructor(storeDefinition))
                .build();
    }

    @NonNull
    private static FieldSpec buildField() {
        return FieldSpec.builder(AndroidTypes.SharedPreferences, "prefs")
                .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
                .build();
    }

    @NonNull
    private static MethodSpec buildConstructor(@NonNull StoreDefinition storeDefinition) {
        return MethodSpec.constructorBuilder()
                .addParameter(ParameterSpec.builder(AndroidTypes.Context, "context").addAnnotation(AnnotationTypes.NonNull).build())
                .addStatement("this.prefs = context.getSharedPreferences($S, $L)", storeDefinition.getPrefsFileName(), storeDefinition.getPrefsMode())
                .build();
    }
}
