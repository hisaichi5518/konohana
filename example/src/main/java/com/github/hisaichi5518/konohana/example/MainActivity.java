package com.github.hisaichi5518.konohana.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.hisaichi5518.konohana.example.store.Item_Store;
import com.github.hisaichi5518.konohana.example.store.Konohana;
import com.github.hisaichi5518.konohana.example.store.User_Store;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Konohana konohana = new Konohana(this);
        final User_Store userStore = konohana.storeOfUser();
        final Item_Store itemStore = konohana.storeOfItem();

        final TextView textView = findViewById(R.id.user_name);
        final Button button = findViewById(R.id.name_changer);

        disposable.add(userStore.nameAsObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                textView.setText(s);
            }
        }));

        disposable.add(itemStore.nameAsObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                button.setText(s);
            }
        }));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> names = Arrays.asList(
                        "yuriko",
                        "yoshitaka",
                        "yoshitaka-yuriko",
                        "yuriko-yoshitaka",
                        "吉高由里子",
                        "由里子",
                        "吉高");
                Collections.shuffle(names);

                String name = names.get(0);
                userStore.setName(name);
                itemStore.setName(name);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
