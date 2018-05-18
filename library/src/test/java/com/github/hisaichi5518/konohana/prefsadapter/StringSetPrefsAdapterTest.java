package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class StringSetPrefsAdapterTest {
    private SharedPreferences prefs;

    @Before
    public void setup() {
        prefs = RuntimeEnvironment.application.getSharedPreferences("tests", Context.MODE_PRIVATE);
    }

    @Test
    public void get_WithDefault() {
        assertThat(StringSetPrefsAdapter.get(prefs, "key", Collections.<String>emptySet()))
                .isEqualTo(Collections.<String>emptySet());

        Set<String> strings = new HashSet<>();
        strings.add("hoge");

        StringSetPrefsAdapter.set(prefs, "key", strings);

        assertThat(StringSetPrefsAdapter.get(prefs, "key", Collections.<String>emptySet()))
                .isEqualTo(strings);
    }

    @Test
    public void get_WithNull() {
        assertThat(StringSetPrefsAdapter.get(prefs, "key", null)).isEqualTo(null);

        StringSetPrefsAdapter.set(prefs, "key", null);

        assertThat(StringSetPrefsAdapter.get(prefs, "key", null))
                .isEqualTo(null);
    }
}