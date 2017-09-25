package com.github.hisaichi5518.konohana.store;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.HashSet;

import io.reactivex.observers.TestObserver;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class StoreClassTest {
    private User_Store store;

    @Before
    public void setup() {
        Konohana konohana = new Konohana(RuntimeEnvironment.application);
        store = konohana.storeOfUser();
    }

    @Test
    public void setter() {
        store.setId(1000);
        assertThat(store.getId()).isEqualTo(1000);
    }

    @Test
    public void getterWithDefaultParameter() {
        assertThat(store.getId(1000)).isEqualTo(1000);
    }

    @Test
    public void getter() {
        assertThat(store.getId()).isEqualTo(0);
        assertThat(store.getName()).isEqualTo("");
        assertThat(store.getRate()).isEqualTo(0F);
        assertThat(store.getLongRate()).isEqualTo(0L);
        assertThat(store.getType()).isEqualTo(User.Type.none);
        assertThat(store.getProductNames()).isEqualTo(new ArrayList<>());
        assertThat(store.getTypes()).isEqualTo(new ArrayList<>());
        assertThat(store.getProducts()).isEqualTo(new ArrayList<>());
        assertThat(store.getStringSet()).isEqualTo(new HashSet<>());
        assertThat(store.getProductIdSet()).isEqualTo(new HashSet<>());
        assertThat(store.getTypeSet()).isEqualTo(new HashSet<>());
        assertThat(store.getProductSet()).isEqualTo(new HashSet<>());
        assertThat(store.getProduct()).isInstanceOf(User.Product.class);
    }

    @Test
    public void has() {
        assertThat(store.hasId()).isFalse();
        store.setId(1000);
        assertThat(store.hasId()).isTrue();
    }

    @Test
    public void remove_NotHaving() {
        assertThat(store.hasId()).isFalse();
        store.removeId();
        assertThat(store.hasId()).isFalse();
    }

    @Test
    public void remove_Having() {
        store.setId(1000);
        assertThat(store.hasId()).isTrue();
        store.removeId();
        assertThat(store.hasId()).isFalse();
    }

    @Test
    public void removeAll() {
        store.setId(1000);
        store.setName("name");
        store.removeAll();

        assertThat(store.hasId()).isFalse();
        assertThat(store.hasName()).isFalse();
    }

    @Test
    public void asObservable() throws Exception {
        TestObserver<Integer> test = store.idAsObservable().test();
        store.setId(1000);

        assertThat(test.values().get(0)).isEqualTo(0); // first value
        assertThat(test.values().get(1)).isEqualTo(1000);
    }

    @Test
    public void asObservable_WithDefaultParameter() throws Exception {
        TestObserver<Integer> test = store.idAsObservable(200).test();
        store.setId(1000);

        assertThat(test.values().get(0)).isEqualTo(200); // first value
        assertThat(test.values().get(1)).isEqualTo(1000);
    }
}