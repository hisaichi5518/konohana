package com.github.hisaichi5518.konohana.processor.types;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;

import java.util.Set;

public class JavaTypes {
    public static final ClassName String = ClassName.get(String.class);

    public static final ClassName Exception = ClassName.get(Exception.class);

    public static final ClassName Set = ClassName.get(Set.class);

    public static final ClassName Enum = ClassName.get(Enum.class);

    public static final ClassName List = ClassName.get(java.util.List.class);

    public static ParameterizedTypeName getSet(ClassName className) {
        return ParameterizedTypeName.get(Set, className);
    }
}
