package com.github.hisaichi5518.konohana.listener;

import android.content.SharedPreferences;

import io.reactivex.subjects.PublishSubject;

public class OnPreferenceChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final PublishSubject<String> subject;

    public OnPreferenceChangeListener(PublishSubject<String> subject) {
        this.subject = subject;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        subject.onNext(s);
    }
}
