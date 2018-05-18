package com.github.hisaichi5518.konohana.processor.definition;

import android.support.annotation.NonNull;

import com.github.hisaichi5518.konohana.annotation.Key;
import com.github.hisaichi5518.konohana.processor.context.ProcessingContext;
import com.github.hisaichi5518.konohana.processor.exception.ProcessingException;
import com.github.hisaichi5518.konohana.processor.model.PrefsAdapter;
import com.github.hisaichi5518.konohana.processor.types.AnnotationTypes;
import com.github.hisaichi5518.konohana.processor.types.JavaTypes;
import com.github.hisaichi5518.konohana.processor.utils.Annotations;
import com.github.hisaichi5518.konohana.processor.utils.Strings;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import java.util.stream.Collectors;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;


public class KeyDefinition implements Contextable {

    private final ProcessingContext context;
    private final VariableElement element;
    private final TypeName fieldTypeName;
    private final String capitalizedName;
    private final Key key;
    private final TypeName prefsAdapter;

    KeyDefinition(@NonNull ProcessingContext context, @NonNull VariableElement element) {
        this.context = context;
        this.element = element;

        fieldTypeName = TypeName.get(element.asType());
        capitalizedName = Strings.upperFirst(element.getSimpleName().toString());

        key = element.getAnnotation(Key.class);

        prefsAdapter = PrefsAdapter.find(this);
        if (prefsAdapter == null) {
            // ex) Can not find available PrefsAdapter for admin field(type: Boolean) of User class
            throw new ProcessingException(
                    "Can not find available PrefsAdapter for "
                            + element.getSimpleName() + " field(type: " + getFieldTypeName().toString() + ")"
                            + " of " + element.getEnclosingElement().getSimpleName() + " class.", element);
        }
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

    public TypeName getBoxedFieldType() {
        return getFieldTypeName().isPrimitive() ? getFieldTypeName().box() : getFieldTypeName();
    }

    public TypeName getCustomPrefsAdapterType() {
        TypeMirror typeMirror = Annotations.getValue(element, Key.class, "prefsAdapter");
        if (typeMirror == null) {
            throw new ProcessingException("prefsAdapter is null", element);
        }

        return ClassName.get(typeMirror);
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

    public String getContainsName() {
        return "has" + capitalizedName;
    }

    public String getRemoverName() {
        return "remove" + capitalizedName;
    }

    public String getAsObservableName() {
        return getFieldName() + "AsObservable";
    }

    public boolean isEnum() {
        TypeElement typeElement = getFieldTypeElement();

        return !typeElement.getSuperclass().getKind().equals(TypeKind.NONE) && ClassName.get(getSupperClassElement(typeElement)).equals(JavaTypes.Enum);
    }

    public boolean isList() {
        TypeElement typeElement = getFieldTypeElement();
        return ClassName.get(typeElement).equals(JavaTypes.List);
    }

    public boolean isSet() {
        TypeElement typeElement = getFieldTypeElement();
        return ClassName.get(typeElement).equals(JavaTypes.Set);
    }

    public TypeName getPrefsAdapter() {
        return prefsAdapter;
    }

    public ClassName getAnnotation() {
        return isNullable() ? AnnotationTypes.Nullable : AnnotationTypes.NonNull;
    }

    private TypeElement getFieldTypeElement() {
        return (TypeElement) context.getTypes().asElement(element.asType());
    }

    private TypeElement getSupperClassElement(TypeElement typeElement) {
        return (TypeElement) context.getTypes().asElement(typeElement.getSuperclass());
    }

    private boolean isNullable() {
        return element.getAnnotationMirrors().stream().filter(c -> {
            return c.getAnnotationType().asElement().getSimpleName().toString().equals("Nullable");
        }).collect(Collectors.toList()).size() > 0;
    }
}
