package com.github.hisaichi5518.konohana;

import com.github.hisaichi5518.konohana.annotation.Key;
import com.github.hisaichi5518.konohana.annotation.Store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class StoreKlassTest {
    @Test
    public void useKlass() {
        Secret konohana = new Secret(RuntimeEnvironment.application);
        assertThat(konohana.storeOfSecret1()).isInstanceOf(Secret1_Store.class);
        assertThat(konohana.storeOfSecret2()).isInstanceOf(Secret2_Store.class);
    }

    @Test
    public void nonUseKlass() {
        Konohana konohana = new Konohana(RuntimeEnvironment.application);
        assertThat(konohana.storeOfTestUser()).isInstanceOf(TestUser_Store.class);
    }

    @Store(klass = "Secret")
    interface Secret1 {
        @Key
        String name = "";
    }

    @Store(klass = "Secret")
    interface Secret2 {
        @Key
        String name = "";
    }
}
