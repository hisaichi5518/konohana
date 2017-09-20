package com.github.hisaichi5518.konohana.processor.definition;

import android.support.annotation.NonNull;

import com.github.hisaichi5518.konohana.processor.context.ProcessingContext;

import java.util.List;
import java.util.stream.Stream;

import javax.lang.model.element.Element;

public class KonohanaDefinition implements Contextable {
    private final ProcessingContext context;
    private final List<StoreDefinition> storeDefinitions;

    public KonohanaDefinition(@NonNull ProcessingContext context, @NonNull List<StoreDefinition> storeDefinitions) {
        this.context = context;
        this.storeDefinitions = storeDefinitions;
    }

    public int getStoreCount() {
        return storeDefinitions.size();
    }

    public Stream<StoreDefinition> storeDefinitionStream() {
        return storeDefinitions.stream();
    }

    @NonNull
    @Override
    public Element element() {
        throw new RuntimeException("not support");
    }

    @NonNull
    @Override
    public ProcessingContext context() {
        return context;
    }

    public String getPackageName() {
        // FIXME: throw Exception if package is null
        return storeDefinitions.get(0).getInterfaceName().packageName();
    }
}
