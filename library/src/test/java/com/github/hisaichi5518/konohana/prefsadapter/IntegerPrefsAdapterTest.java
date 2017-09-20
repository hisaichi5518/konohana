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
public class IntegerPrefsAdapterTest {

    private SharedPreferences prefs;

    @Before
    public void setup() {
        prefs = RuntimeEnvironment.application.getSharedPreferences("tests", Context.MODE_PRIVATE);
    }

    @Test
    public void get_WithDefault() throws Exception {
        assertThat(IntegerPrefsAdapter.get(prefs, "key", 0)).isEqualTo(0);

        IntegerPrefsAdapter.set(prefs, "key", 1);

        assertThat(IntegerPrefsAdapter.get(prefs, "key", 0)).isEqualTo(1);
    }
}