package com.github.hisaichi5518.konohana.processor.exception;

import javax.lang.model.element.Element;

public class ProcessingException extends RuntimeException {
    private final Element element;

    public ProcessingException(String message, Element element) {
        super(message);
        this.element = element;
    }

    public Element getElement() {
        return element;
    }
}
