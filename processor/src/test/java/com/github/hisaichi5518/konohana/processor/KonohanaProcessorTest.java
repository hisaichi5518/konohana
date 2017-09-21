package com.github.hisaichi5518.konohana.processor;

import com.google.common.truth.Truth;
import com.google.testing.compile.JavaFileObjects;
import com.google.testing.compile.JavaSourceSubjectFactory;

import org.junit.Test;

public class KonohanaProcessorTest {
    @Test
    public void test() {
        Truth.assert_().about(JavaSourceSubjectFactory.javaSource())
                .that(JavaFileObjects.forResource("Success.java"))
                .processedWith(new KonohanaProcessor())
                .compilesWithoutError();
    }
}