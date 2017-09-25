package com.github.hisaichi5518.konohana.store;

import com.github.hisaichi5518.konohana.annotation.Key;
import com.github.hisaichi5518.konohana.annotation.Store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class KeyPatternTest {
    @Test
    public void getter_Default() {
        KeyPatternStore_Store store = new KeyPatternStore_Store(RuntimeEnvironment.application);

        assertThat(store.getId()).isEqualTo(0);
        assertThat(store.getName()).isEqualTo("");
        assertThat(store.getRate()).isEqualTo(0F);
        assertThat(store.getLongRate()).isEqualTo(0L);
        assertThat(store.getType()).isEqualTo(KeyPatternStore.Type.none);
        assertThat(store.getProductNames()).isEqualTo(new ArrayList<>());
        assertThat(store.getTypes()).isEqualTo(new ArrayList<>());
        assertThat(store.getProducts()).isEqualTo(new ArrayList<>());
        assertThat(store.getStringSet()).isEqualTo(new HashSet<>());
        assertThat(store.getProductIdSet()).isEqualTo(new HashSet<>());
        assertThat(store.getTypeSet()).isEqualTo(new HashSet<>());
        assertThat(store.getProductSet()).isEqualTo(new HashSet<>());
        assertThat(store.getProduct()).isInstanceOf(KeyPatternStore.Product.class);
    }

    @Store(klass = "KeyPattern")
    interface KeyPatternStore {
        @Key
        int id = 0;

        @Key
        String name = "";

        @Key
        float rate = 0F;

        @Key
        long longRate = 0L;

        @Key
        Type type = Type.none;

        @Key
        List<String> productNames = new ArrayList<>();

        @Key
        List<Type> types = new ArrayList<>();

        @Key
        List<Product> products = new ArrayList<>();

        @Key
        Set<String> stringSet = new HashSet<>();

        @Key
        Set<Integer> productIdSet = new HashSet<>();

        @Key
        Set<Type> typeSet = new HashSet<>();

        @Key
        Set<Product> productSet = new HashSet<>();

        @Key
        Product product = new Product();

        enum Type {
            none
        }

        class Product {
            String name;
        }
    }
}
