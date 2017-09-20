package com.github.hisaichi5518.konohana.processor.definition;

import android.support.annotation.NonNull;

import com.github.hisaichi5518.konohana.annotation.Key;
import com.github.hisaichi5518.konohana.processor.context.ProcessingContext;
import com.github.hisaichi5518.konohana.processor.utils.Strings;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;


public class KeyDefinition implements Contextable {

    private final ProcessingContext context;
    private final VariableElement element;
    private final TypeName fieldTypeName;
    private final String capitalizedName;
    private final Key key;

    KeyDefinition(@NonNull ProcessingContext context, @NonNull VariableElement element) {
        this.context = context;
        this.element = element;

        fieldTypeName = TypeName.get(element.asType());
        capitalizedName = Strings.upperFirst(element.getSimpleName().toString());

        key = element.getAnnotation(Key.class);
    }

    @NonNull
    @Override
    public Element element() {
        return element;
    }

    @NonNull
    @Override
    public ProcessingContext context() {
        return context;
    }

    public String getFieldName() {
        return element.getSimpleName().toString();
    }

    public TypeName getFieldTypeName() {
        return fieldTypeName;
    }

    public String getPrefsKeyName() {
        if (key.name().isEmpty()) {
            return element.getSimpleName().toString();
        }

        return key.name();
    }

    public String getGetterName() {
        return "get" + capitalizedName;
    }

    public String getSetterName() {
        return "set" + capitalizedName;
    }
}
