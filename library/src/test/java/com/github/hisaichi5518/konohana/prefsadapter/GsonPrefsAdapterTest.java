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
public class GsonPrefsAdapterTest {

    private SharedPreferences prefs;

    @Before
    public void setup() {
        prefs = RuntimeEnvironment.application.getSharedPreferences("tests", Context.MODE_PRIVATE);
    }

    @Test
    public void get_WithDefault() throws Exception {
        assertThat(GsonPrefsAdapter.get(prefs, "key", new User()).name).isEqualTo(null);

        User user = new User();
        user.name = "yuriko";
        GsonPrefsAdapter.set(prefs, "key", user);

        assertThat(GsonPrefsAdapter.get(prefs, "key", new User()).name).isEqualTo("yuriko");
    }

    private static class User {
        String name;
    }
}