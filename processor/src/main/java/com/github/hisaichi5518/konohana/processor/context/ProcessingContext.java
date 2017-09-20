package com.github.hisaichi5518.konohana.processor.context;

import android.support.annotation.NonNull;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

public class ProcessingContext {
    public final ProcessingEnvironment processingEnvironment;
    public final RoundEnvironment roundEnvironment;
    private final Types types;

    public ProcessingContext(@NonNull ProcessingEnvironment processingEnvironment, @NonNull RoundEnvironment roundEnvironment) {
        this.processingEnvironment = processingEnvironment;
        this.roundEnvironment = roundEnvironment;

        types = processingEnvironment.getTypeUtils();
    }

    public void note(@NonNull String message) {
        processingEnvironment.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
    }

    public void error(@NonNull String message, @NonNull Element element) {
        processingEnvironment.getMessager().printMessage(Diagnostic.Kind.ERROR, message, element);
    }

    public Types getTypes() {
        return types;
    }
}
