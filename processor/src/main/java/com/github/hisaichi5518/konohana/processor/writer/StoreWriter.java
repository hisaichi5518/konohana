package com.github.hisaichi5518.konohana.processor.writer;

import android.support.annotation.NonNull;

import com.github.hisaichi5518.konohana.processor.builder.StoreMethods;
import com.github.hisaichi5518.konohana.processor.definition.StoreDefinition;
import com.github.hisaichi5518.konohana.processor.types.AndroidTypes;
import com.github.hisaichi5518.konohana.processor.types.AnnotationTypes;
import com.github.hisaichi5518.konohana.processor.types.JavaTypes;
import com.github.hisaichi5518.konohana.processor.types.KonohanaTypes;
import com.github.hisaichi5518.konohana.processor.types.RxJavaTypes;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.lang.model.element.Modifier;

public class StoreWriter {

    private final StoreDefinition storeDefinition;

    private StoreWriter(StoreDefinition storeDefinition) {
        this.storeDefinition = storeDefinition;
    }

    public static void write(@NonNull StoreDefinition storeDefinition) {
        new StoreWriter(storeDefinition).write();
    }

    @NonNull
    private TypeSpec buildTypeSpec() {
        return TypeSpec.classBuilder(storeDefinition.getStoreClassName())
                .addSuperinterface(storeDefinition.getInterfaceName())
                .addModifiers(Modifier.PUBLIC)
                .addField(buildPrefsField())
                .addField(buildSubjectField())
                .addField(buildListenerField())
                .addMethod(buildConstructor())
                .addMethods(StoreMethods.build(storeDefinition))
                .build();
    }

    @NonNull
    private FieldSpec buildPrefsField() {
        return FieldSpec.builder(AndroidTypes.SharedPreferences, "prefs")
                .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
                .addAnnotation(AnnotationTypes.NonNull)
                .build();
    }

    @NonNull
    private FieldSpec buildListenerField() {
        return FieldSpec.builder(KonohanaTypes.OnPreferenceChangeListener, "listener")
                .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
                .addAnnotation(AnnotationTypes.NonNull)
                .build();
    }

    @NonNull
    private FieldSpec buildSubjectField() {
        return FieldSpec.builder(RxJavaTypes.getPublishSubject(JavaTypes.String), "subject")
                .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
                .addAnnotation(AnnotationTypes.NonNull)
                .build();
    }

    @NonNull
    private MethodSpec buildConstructor() {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(AndroidTypes.Context, "context").addAnnotation(AnnotationTypes.NonNull).build())
                .addStatement("this.prefs = context.getSharedPreferences($S, $L)", storeDefinition.getPrefsFileName(), storeDefinition.getPrefsMode())
                .addStatement("this.subject = PublishSubject.create()")
                .addStatement("this.listener = new $T(subject)", KonohanaTypes.OnPreferenceChangeListener)
                .addStatement("prefs.registerOnSharedPreferenceChangeListener(listener)")
                .beginControlFlow("subject.doOnDispose(new $T()", RxJavaTypes.Action)
                .addCode("@$T\n", AnnotationTypes.Override)
                .beginControlFlow("public void run() throws $T", JavaTypes.Exception)
                .addStatement("prefs.unregisterOnSharedPreferenceChangeListener(listener)")
                .endControlFlow()
                .endControlFlow(")")
                .build();
    }

    private void write() {
        try {
            JavaFile.builder(storeDefinition.getInterfaceName().packageName(), buildTypeSpec())
                    .build()
                    .writeTo(storeDefinition.getFiler());
        } catch (IOException e) {
            throw storeDefinition.newProcessingException(e);
        }
    }
}
