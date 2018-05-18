package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class StringPrefsAdapterTest {

    private SharedPreferences prefs;

    @Before
    public void setup() {
        prefs = RuntimeEnvironment.application.getSharedPreferences("tests", Context.MODE_PRIVATE);
    }

    @Test
    public void get_WithDefault() {
        assertThat(StringPrefsAdapter.get(prefs, "key", "")).isEqualTo("");

        StringPrefsAdapter.set(prefs, "key", "value");

        assertThat(StringPrefsAdapter.get(prefs, "key", "")).isEqualTo("value");
    }

    @Test
    public void get_WithNull() {
        assertThat(StringPrefsAdapter.get(prefs, "key", null)).isEqualTo(null);

        StringPrefsAdapter.set(prefs, "key", null);

        assertThat(StringPrefsAdapter.get(prefs, "key", null)).isEqualTo(null);
    }
}
