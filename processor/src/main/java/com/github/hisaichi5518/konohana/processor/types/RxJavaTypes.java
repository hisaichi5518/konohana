package com.github.hisaichi5518.konohana.processor.types;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class RxJavaTypes {

    private static final String PACKAGE = "io.reactivex";

    private static final String FUNCTIONS_PACKAGE = PACKAGE + ".functions";

    public static final ClassName Observable = ClassName.get(PACKAGE, "Observable");

    public static final ClassName ObservableEmitter = ClassName.get(PACKAGE, "ObservableEmitter");

    public static final ClassName ObservableOnSubscribe = ClassName.get(PACKAGE, "ObservableOnSubscribe");

    public static final ClassName Cancellable = ClassName.get(FUNCTIONS_PACKAGE, "Cancellable");

    public static final ClassName Predicate = ClassName.get(FUNCTIONS_PACKAGE, "Predicate");

    public static final ClassName Function = ClassName.get(FUNCTIONS_PACKAGE, "Function");

    public static ParameterizedTypeName getObservable(TypeName className) {
        return ParameterizedTypeName.get(Observable, className);
    }

    public static ParameterizedTypeName getPredicate(TypeName className) {
        return ParameterizedTypeName.get(Predicate, className);
    }
}
