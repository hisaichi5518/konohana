package com.github.hisaichi5518.konohana.processor.exception;

import android.support.annotation.NonNull;

import javax.lang.model.element.Element;

public class ProcessingException extends RuntimeException {
    private final Element element;

    public ProcessingException(@NonNull String message, @NonNull Element element) {
        super(message);
        this.element = element;
    }

    public ProcessingException(@NonNull Throwable e, @NonNull Element element) {
        super(e);
        this.element = element;
    }

    @NonNull
    public Element getElement() {
        return element;
    }
}
