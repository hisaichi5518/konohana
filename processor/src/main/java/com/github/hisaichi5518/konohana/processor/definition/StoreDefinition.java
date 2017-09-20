package com.github.hisaichi5518.konohana.processor.definition;

import com.github.hisaichi5518.konohana.annotation.Store;
import com.github.hisaichi5518.konohana.processor.context.ProcessingContext;
import com.github.hisaichi5518.konohana.processor.exception.ProcessingException;
import com.squareup.javapoet.ClassName;

import java.util.stream.Stream;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;


public class StoreDefinition {

    private final ProcessingContext context;
    private final TypeElement element;
    private final ClassName interfaceName;
    private final ClassName storeClassName;
    private final Store store;

    public static Stream<StoreDefinition> createStream(ProcessingContext context) {
        return context.roundEnvironment.getElementsAnnotatedWith(Store.class)
                .stream()
                .filter(element -> element.getKind() == ElementKind.INTERFACE)
                .map(element -> new StoreDefinition(context, (TypeElement) element));
    }

    private StoreDefinition(ProcessingContext context, TypeElement element) {
        this.context = context;
        this.element = element;

        interfaceName = ClassName.get(element);
        storeClassName = ClassName.get(interfaceName.packageName(), interfaceName.simpleName() + "_Store");

        store = element.getAnnotation(Store.class);
    }

    public ClassName getStoreClassName() {
        return storeClassName;
    }

    public ClassName getInterfaceName() {
        return interfaceName;
    }

    public String getPrefsFileName() {
        if (store.name().isEmpty()) {
            return element.getSimpleName().toString();
        }

        return store.name();
    }

    public int getPrefsMode() {
        if (store.mode() < 0) {
            throw new ProcessingException("Invalid model!", element);
        }

        return store.mode();
    }
}
