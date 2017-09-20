package com.github.hisaichi5518.konohana.processor.types;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;

public class RxJavaTypes {

    private static final String PACKAGE = "io.reactivex";

    public static final ClassName Observable = ClassName.get(PACKAGE, "Observable");

    public static final ClassName ObservableEmitter = ClassName.get(PACKAGE, "ObservableEmitter");

    public static final ClassName ObservableOnSubscribe = ClassName.get(PACKAGE, "ObservableOnSubscribe");

    public static final ClassName Cancellable = ClassName.get(PACKAGE + ".functions", "Cancellable");

    public static ParameterizedTypeName getObservable(ClassName className) {
        return ParameterizedTypeName.get(Observable, className);
    }
}
