package com.github.hisaichi5518.konohana.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.hisaichi5518.konohana.example.store.Konohana;
import com.github.hisaichi5518.konohana.example.store.User_Store;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final User_Store userStore = new Konohana(this).storeOfUser();

        final TextView textView = findViewById(R.id.user_name);
        userStore.nameAsObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                textView.setText(s);
            }
        });


        final Button button = findViewById(R.id.name_changer);
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

                userStore.setName(names.get(0));
            }
        });
    }
}
