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
public class SetPrefsAdapterTest {
    private SharedPreferences prefs;

    @Before
    public void setup() {
        prefs = RuntimeEnvironment.application.getSharedPreferences("tests", Context.MODE_PRIVATE);
    }

    @Test
    public void get() throws Exception {
        assertThat(SetPrefsAdapter.get(prefs, "key", new HashSet<>()).size()).isEqualTo(0);

        User user = new User();
        user.name = "yuriko";

        Set<User> users = new HashSet<>();
        users.add(user);
        SetPrefsAdapter.set(prefs, "key", users);

        Set<User> result = SetPrefsAdapter.get(prefs, "key", Collections.<User>emptySet());
        assertThat(result.iterator().next().name).isEqualTo("yuriko");
    }

    private static class User {
        String name;
    }
}