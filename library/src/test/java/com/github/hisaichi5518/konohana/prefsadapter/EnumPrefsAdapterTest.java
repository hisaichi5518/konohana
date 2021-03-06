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
public class EnumPrefsAdapterTest {

    private SharedPreferences prefs;

    @Before
    public void setup() {
        prefs = RuntimeEnvironment.application.getSharedPreferences("tests", Context.MODE_PRIVATE);
    }

    @Test
    public void get_WithDefault() {
        assertThat(EnumPrefsAdapter.get(prefs, "key", Type.none)).isEqualTo(Type.none);

        EnumPrefsAdapter.set(prefs, "key", Type.buyer);

        assertThat(EnumPrefsAdapter.get(prefs, "key", Type.none)).isEqualTo(Type.buyer);
    }

    @Test
    public void get_WithNull() {
        assertThat(EnumPrefsAdapter.get(prefs, "key", null)).isEqualTo(null);

        EnumPrefsAdapter.set(prefs, "key", null);

        assertThat(EnumPrefsAdapter.get(prefs, "key", null)).isEqualTo(null);
    }

    private enum Type {
        none, buyer
    }
}