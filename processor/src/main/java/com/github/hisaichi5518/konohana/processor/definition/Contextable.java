package com.github.hisaichi5518.konohana.processor.definition;

import android.support.annotation.NonNull;

import com.github.hisaichi5518.konohana.processor.context.ProcessingContext;
import com.github.hisaichi5518.konohana.processor.exception.ProcessingException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;

interface Contextable {

    @NonNull
    Element element();

    @NonNull
    ProcessingContext context();

    @NonNull
    default ProcessingException newProcessingException(@NonNull Throwable e) {
        return new ProcessingException(e, element());
    }

    @NonNull
    default Filer getFiler() {
        return context().processingEnvironment.getFiler();
    }

}
