package com.github.hisaichi5518.konohana.prefsadapter;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ListPrefsAdapterTest {
    private SharedPreferences prefs;

    @Before
    public void setup() {
        prefs = RuntimeEnvironment.application.getSharedPreferences("tests", Context.MODE_PRIVATE);
    }

    @Test
    public void get() throws Exception {
        assertThat(ListPrefsAdapter.get(prefs, "key", new ArrayList<User>()).size()).isEqualTo(0);

        User user = new User();
        user.name = "yuriko";

        List<User> users = new ArrayList<>();
        users.add(user);
        ListPrefsAdapter.set(prefs, "key", users);


        List<User> empty = new ArrayList<>();
        assertThat(ListPrefsAdapter.get(prefs, "key", empty).get(0).name).isEqualTo("yuriko");
    }

    static class User {
        String name;
    }
}