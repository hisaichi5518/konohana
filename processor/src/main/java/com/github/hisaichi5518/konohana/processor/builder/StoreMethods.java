package com.github.hisaichi5518.konohana.processor.builder;

import com.github.hisaichi5518.konohana.processor.definition.KeyDefinition;
import com.github.hisaichi5518.konohana.processor.definition.StoreDefinition;
import com.github.hisaichi5518.konohana.processor.types.AndroidTypes;
import com.github.hisaichi5518.konohana.processor.types.AnnotationTypes;
import com.github.hisaichi5518.konohana.processor.types.RxJavaTypes;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

public class StoreMethods {
    public static List<MethodSpec> build(StoreDefinition storeDefinition) {
        List<MethodSpec> specs = new ArrayList<>();

        specs.add(buildRemoveAllSpec());

        storeDefinition.keyDefinitionStream().forEach(keyDefinition -> {
            specs.add(buildGetterSpec(storeDefinition, keyDefinition));
            specs.add(buildGetterWithParameterSpec(keyDefinition));
            specs.add(buildSetterSpec(keyDefinition));
            specs.add(buildContainsSpec(keyDefinition));
            specs.add(buildRemoverSpec(keyDefinition));
            specs.add(buildKeyAsObservableSpec(keyDefinition));
            specs.add(buildKeyAsObservableSpecWithParameter(keyDefinition));
        });

        return specs;
    }

    private static MethodSpec buildRemoveAllSpec() {
        return MethodSpec.methodBuilder("removeAll")
                .addModifiers(Modifier.PUBLIC)
                .addStatement("prefs.edit().clear().apply()")
                .build();
    }

    private static MethodSpec buildGetterSpec(StoreDefinition storeDefinition, KeyDefinition keyDefinition) {
        return MethodSpec.methodBuilder(keyDefinition.getGetterName())
                .returns(keyDefinition.getFieldTypeName())
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationTypes.NonNull)
                .addStatement("return $T.get(prefs, $S, $T.$L)",
                        keyDefinition.getPrefsAdapter(), keyDefinition.getPrefsKeyName(), storeDefinition.getInterfaceName(), keyDefinition.getFieldName())
                .build();
    }

    private static MethodSpec buildGetterWithParameterSpec(KeyDefinition keyDefinition) {
        return MethodSpec.methodBuilder(keyDefinition.getGetterName())
                .returns(keyDefinition.getFieldTypeName())
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationTypes.NonNull)
                .addParameter(ParameterSpec.builder(keyDefinition.getFieldTypeName(), "defaultValue").addAnnotation(AnnotationTypes.NonNull).build())
                .addStatement("return $T.get(prefs, $S, defaultValue)",
                        keyDefinition.getPrefsAdapter(), keyDefinition.getPrefsKeyName())
                .build();
    }

    private static MethodSpec buildSetterSpec(KeyDefinition keyDefinition) {
        return MethodSpec.methodBuilder(keyDefinition.getSetterName())
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(keyDefinition.getFieldTypeName(), "value").addAnnotation(AnnotationTypes.NonNull).build())
                .addStatement("$T.set(prefs, $S, value)",
                        keyDefinition.getPrefsAdapter(), keyDefinition.getPrefsKeyName())
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

    private static MethodSpec buildKeyAsObservableSpec(KeyDefinition keyDefinition) {
        return MethodSpec.methodBuilder(keyDefinition.getAsObservableName())
                .addModifiers(Modifier.PUBLIC)
                .returns(RxJavaTypes.getObservable(keyDefinition.getBoxedFieldType()))
                .beginControlFlow("return subject.filter(new $T<String>()", RxJavaTypes.Predicate)
                .addCode("@$T\n", AnnotationTypes.Override)
                .beginControlFlow("public boolean test(String v) throws Exception")
                .addStatement("return $T.equals(v, $S)", AndroidTypes.TextUtils, keyDefinition.getPrefsKeyName())
                .endControlFlow()
                .endControlFlow()
                .addCode(").startWith($S)", "<dummy>") // // Dummy value to trigger initial load.
                .beginControlFlow(".map(new $T<String, $T>()", RxJavaTypes.Function, keyDefinition.getBoxedFieldType())
                .addCode("@$T\n", AnnotationTypes.Override)
                .beginControlFlow("public $T apply(String s) throws Exception", keyDefinition.getBoxedFieldType())
                .addStatement("return $L()", keyDefinition.getGetterName())
                .endControlFlow()
                .endControlFlow(")")
                .build();
    }

    private static MethodSpec buildKeyAsObservableSpecWithParameter(KeyDefinition keyDefinition) {
        return MethodSpec.methodBuilder(keyDefinition.getAsObservableName())
                .addModifiers(Modifier.PUBLIC)
                .returns(RxJavaTypes.getObservable(keyDefinition.getBoxedFieldType()))
                .addParameter(ParameterSpec.builder(keyDefinition.getFieldTypeName(), "defaultValue").addModifiers(Modifier.FINAL).addAnnotation(AnnotationTypes.NonNull).build())
                .beginControlFlow("return subject.filter(new $T<String>()", RxJavaTypes.Predicate)
                .addCode("@$T\n", AnnotationTypes.Override)
                .beginControlFlow("public boolean test(String v) throws Exception")
                .addStatement("return $T.equals(v, $S)", AndroidTypes.TextUtils, keyDefinition.getPrefsKeyName())
                .endControlFlow()
                .endControlFlow()
                .addCode(").startWith($S)", "<dummy>") // // Dummy value to trigger initial load.
                .beginControlFlow(".map(new $T<String, $T>()", RxJavaTypes.Function, keyDefinition.getBoxedFieldType())
                .addCode("@$T\n", AnnotationTypes.Override)
                .beginControlFlow("public $T apply(String s) throws Exception", keyDefinition.getBoxedFieldType())
                .addStatement("return $L(defaultValue)", keyDefinition.getGetterName())
                .endControlFlow()
                .endControlFlow(")")
                .build();
    }
}
