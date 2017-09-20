package com.github.hisaichi5518.konohana.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface Key {
    String name() default "";

    Class<?> prefsAdapter() default UseBuildInPrefsAdapter.class;

    class UseBuildInPrefsAdapter {}
}
