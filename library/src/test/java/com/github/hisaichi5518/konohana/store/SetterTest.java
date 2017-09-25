package com.github.hisaichi5518.konohana.store;

import com.github.hisaichi5518.konohana.annotation.Key;
import com.github.hisaichi5518.konohana.annotation.Store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class SetterTest {
    @Test
    public void setter() {
        SetterStore_Store store = new SetterStore_Store(RuntimeEnvironment.application);

        store.setId(1000);
        assertThat(store.getId()).isEqualTo(1000);
    }

    @Store(klass = "Setter")
    interface SetterStore {
        @Key
        int id = 0;
    }
}