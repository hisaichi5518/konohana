package com.github.hisaichi5518.konohana.processor.definition;

import android.support.annotation.NonNull;

import com.github.hisaichi5518.konohana.annotation.Key;
import com.github.hisaichi5518.konohana.annotation.Store;
import com.github.hisaichi5518.konohana.processor.context.ProcessingContext;
import com.github.hisaichi5518.konohana.processor.exception.ProcessingException;
import com.squareup.javapoet.ClassName;

import java.util.stream.Stream;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;


public class StoreDefinition implements Contextable {

    private final ProcessingContext context;
    private final TypeElement element;
    private final ClassName interfaceName;
    private final ClassName storeClassName;
    private final Store store;

    public static Stream<StoreDefinition> createStream(@NonNull ProcessingContext context) {
        return context.roundEnvironment.getElementsAnnotatedWith(Store.class)
                .stream()
                .filter(element -> element.getKind() == ElementKind.INTERFACE)
                .map(element -> new StoreDefinition(context, (TypeElement) element));
    }

    private StoreDefinition(@NonNull ProcessingContext context, @NonNull TypeElement element) {
        this.context = context;
        this.element = element;

        interfaceName = ClassName.get(element);
        storeClassName = ClassName.get(interfaceName.packageName(), interfaceName.simpleName() + "_Store");

        store = element.getAnnotation(Store.class);
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

    @NonNull
    public ClassName getStoreClassName() {
        return storeClassName;
    }

    @NonNull
    public ClassName getInterfaceName() {
        return interfaceName;
    }

    @NonNull
    public String getPrefsFileName() {
        if (store.name().isEmpty()) {
            return element.getSimpleName().toString();
        }

        return store.name();
    }

    @NonNull
    public String getKlass() {
        return store.klass();
    }

    public int getPrefsMode() {
        if (store.mode() < 0) {
            throw new ProcessingException("Invalid model!", element);
        }

        return store.mode();
    }

    @NonNull
    public Stream<KeyDefinition> keyDefinitionStream() {
        return element.getEnclosedElements()
                .stream()
                .filter(e -> e.getAnnotation(Key.class) != null)
                .map(e -> new KeyDefinition(context, (VariableElement) e));
    }
}
