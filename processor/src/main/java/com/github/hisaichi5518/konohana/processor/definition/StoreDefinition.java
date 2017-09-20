package com.github.hisaichi5518.konohana.processor.definition;

import com.github.hisaichi5518.konohana.annotation.Store;
import com.github.hisaichi5518.konohana.processor.context.ProcessingContext;

import java.util.stream.Stream;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

public class StoreDefinition {

    private final ProcessingContext context;
    private final TypeElement element;

    public static Stream<StoreDefinition> createStream(ProcessingContext context) {
        return context.roundEnvironment.getElementsAnnotatedWith(Store.class)
                .stream()
                .filter(element -> element.getKind() == ElementKind.INTERFACE)
                .map(element -> new StoreDefinition(context, (TypeElement) element));
    }

    private StoreDefinition(ProcessingContext context, TypeElement element) {
        this.context = context;
        this.element = element;
    }

}
