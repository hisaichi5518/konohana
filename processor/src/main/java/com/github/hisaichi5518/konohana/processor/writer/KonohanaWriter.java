package com.github.hisaichi5518.konohana.processor.writer;

import com.github.hisaichi5518.konohana.processor.definition.KonohanaDefinition;
import com.github.hisaichi5518.konohana.processor.types.AndroidTypes;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

public class KonohanaWriter {
    private final KonohanaDefinition konohanaDefinition;

    public static void write(KonohanaDefinition konohanaDefinition) {
        if (konohanaDefinition.getStoreCount() == 0) {
            return;
        }

        new KonohanaWriter(konohanaDefinition).write();
    }

    private KonohanaWriter(KonohanaDefinition konohanaDefinition) {
        this.konohanaDefinition = konohanaDefinition;
    }

    private void write() {
        try {
            JavaFile.builder(konohanaDefinition.getPackageName(), buildTypeSpec())
                    .build()
                    .writeTo(konohanaDefinition.getFiler());
        } catch (IOException e) {
            throw new RuntimeException("KonohanaWriter: " + e.getMessage());
        }
    }

    private TypeSpec buildTypeSpec() {
        return TypeSpec.classBuilder(konohanaDefinition.getKlass())
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addField(buildFieldSpec())
                .addMethod(buildConstructor())
                .addMethods(buildMethods())
                .build();
    }

    private FieldSpec buildFieldSpec() {
        return FieldSpec.builder(AndroidTypes.Context, "applicationContext")
                .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
                .build();
    }

    private MethodSpec buildConstructor() {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(AndroidTypes.Context, "context").build())
                .addStatement("this.applicationContext = context.getApplicationContext()")
                .build();
    }

    private List<MethodSpec> buildMethods() {
        List<MethodSpec> specs = new ArrayList<>();

        konohanaDefinition.storeDefinitionStream().forEach(storeContext -> {
            specs.add(MethodSpec.methodBuilder("storeOf" + storeContext.getInterfaceName().simpleName())
                    .addModifiers(Modifier.PUBLIC)
                    .returns(storeContext.getStoreClassName())
                    .addStatement("return new $T(applicationContext)", storeContext.getStoreClassName())
                    .build());
        });

        return specs;

    }
}
